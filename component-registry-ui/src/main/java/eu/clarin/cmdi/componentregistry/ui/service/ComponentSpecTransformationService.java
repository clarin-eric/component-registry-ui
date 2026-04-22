/*
 * Copyright (C) 2024 CLARIN ERIC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.clarin.cmdi.componentregistry.ui.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.TypeRef;
import eu.clarin.cmdi.componentregistry.openapi.client.model.Attribute;
import eu.clarin.cmdi.componentregistry.openapi.client.model.ComponentSpec;
import eu.clarin.cmdi.componentregistry.openapi.client.model.ComponentType;
import eu.clarin.cmdi.componentregistry.openapi.client.model.ElementType;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author twagoo
 */
@Service
@Slf4j
public class ComponentSpecTransformationService {
    
    private final ObjectMapper objectMapper;
    private final Configuration configuration;
    private final SpecPartsFactory specPartsFactory;
    
    private final static Pattern ARRAY_PATTERN = Pattern.compile("(.*)\\[(\\d)\\]$");
    
    private static final TypeRef<List<ComponentType>> COMPONENT_TYPE = new TypeRef<List<ComponentType>>() {
    };
    private static final TypeRef<List<ElementType>> ELEMENT_TYPE = new TypeRef<List<ElementType>>() {
    };
    
    public ComponentSpecTransformationService(ObjectMapper objectMapper, com.jayway.jsonpath.Configuration jsonPathConfiguration, SpecPartsFactory specPartsFactory) {
        this.objectMapper = objectMapper;
        this.configuration = jsonPathConfiguration;
        this.specPartsFactory = specPartsFactory;
    }
    
    public ComponentSpec deletePath(ComponentSpec spec, String path) throws JsonProcessingException {
        final DocumentContext doc = readSpecAsJson(spec);
        if (path != null) {
            // remove node at path
            doc.delete("$." + path, new Predicate[]{});
        }

        //convert doc back to object
        return objectMapper.readValue(doc.jsonString(), ComponentSpec.class);
    }
    
    public ComponentSpec addChildComponent(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return addChildItemToComponent(spec, path,
                parent -> parent.addComponentItem(specPartsFactory.newComponent())
        );
    }

    public ComponentSpec linkChildComponent(ComponentSpec spec, String path, String linkId) throws JsonProcessingException, ComponentSpecTransformationException {
        return addChildItemToComponent(spec, path,
                parent -> parent.addComponentItem(specPartsFactory.newComponent().componentRef(linkId))
        );
    }
    
    public ComponentSpec addChildElement(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return addChildItemToComponent(spec, path,
                parent -> parent.addElementItem(specPartsFactory.newElement())
        );
    }
    
    public ComponentSpec addChildAttributeToComponent(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return addChildItemToComponent(spec, path,
                component -> {
                    specPartsFactory.addToAttributeList(component::getAttributeList, component::setAttributeList);
                }
        );
    }
    
    public ComponentSpec addChildAttributeToElement(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return addChildItemToElement(spec, path,
                element -> {
                    specPartsFactory.addToAttributeList(element::getAttributeList, element::setAttributeList);
                });
    }
    
    private ComponentSpec addChildItemToComponent(ComponentSpec spec, String path, Consumer<ComponentType> addLogic) throws JsonProcessingException, ComponentSpecTransformationException {
        return addChildItem(spec, path, addLogic, new TypeRef<ComponentType>() {
        });
    }
    
    private ComponentSpec addChildItemToElement(ComponentSpec spec, String path, Consumer<ElementType> addLogic) throws JsonProcessingException, ComponentSpecTransformationException {
        return addChildItem(spec, path, addLogic, new TypeRef<ElementType>() {
        });
    }
    
    private <T> ComponentSpec addChildItem(ComponentSpec spec, String path, Consumer<T> addLogic, TypeRef<T> typeRef) throws JsonProcessingException, ComponentSpecTransformationException {
        final DocumentContext doc = readSpecAsJson(spec);
        try {
            final T parent = doc.read("$." + path, typeRef);
            
            if (parent != null) {
                addLogic.accept(parent);
                doc.set("$." + path, parent);
                
                return objectMapper.readValue(doc.jsonString(), ComponentSpec.class);
            }
        } catch (JsonProcessingException | JsonPathException ex) {
            log.warn("Failed to add item at path: " + path, ex);
        }
        throw new ComponentSpecTransformationException(String.format("Could not add item to spec at [%s]", path));
    }
    
    public ComponentSpec insertComponentBefore(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return insertItemBefore(spec, path, specPartsFactory::newComponent, COMPONENT_TYPE);
    }
    
    public ComponentSpec insertElementBefore(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return insertItemBefore(spec, path, specPartsFactory::newElement, ELEMENT_TYPE);
    }
    
    private <T> ComponentSpec insertItemBefore(ComponentSpec spec, String path, Supplier<T> constructor, TypeRef<List<T>> typeRef) throws JsonProcessingException, ComponentSpecTransformationException {
        final DocumentContext doc = readSpecAsJson(spec);

        //extract index
        final Matcher matcher = ARRAY_PATTERN.matcher(path);
        if (matcher.matches()) {
            try {
                final String arrayPath = matcher.group(1);
                final String indexString = matcher.group(2);
                if (arrayPath != null && indexString != null) {
                    final int index = Integer.parseInt(indexString);
                    //get containing list
                    final List<T> containerArray = doc.read(arrayPath, typeRef);
                    //insert item
                    containerArray.add(index, constructor.get());
                    //replace in context
                    doc.set(arrayPath, containerArray);
                    return objectMapper.readValue(doc.jsonString(), ComponentSpec.class);
                }
            } catch (JsonProcessingException | JsonPathException | IndexOutOfBoundsException ex) {
                log.warn("Failed to add item at path: " + path, ex);
            } catch (NumberFormatException ex) {
                log.warn("Not a valid index in path:" + path);
            }
        }
        throw new ComponentSpecTransformationException(String.format("Could not insert an item before %s", path));
        
    }
    
    public ComponentSpec moveComponentUp(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return moveItem(spec, path, -1, COMPONENT_TYPE);
    }
    
    public ComponentSpec moveComponentDown(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return moveItem(spec, path, +1, COMPONENT_TYPE);
    }
    
    public ComponentSpec moveElementUp(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return moveItem(spec, path, -1, ELEMENT_TYPE);
    }
    
    public ComponentSpec moveElementDown(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return moveItem(spec, path, +1, ELEMENT_TYPE);
    }
    
    public ComponentSpec moveAttributeUp(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return moveItem(spec, path, -1, new TypeRef<List<Attribute>>() {
        });
    }
    
    public ComponentSpec moveAttributeDown(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        return moveItem(spec, path, +1, new TypeRef<List<Attribute>>() {
        });
    }
    
    public ElementType extractElement(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        final DocumentContext doc = readSpecAsJson(spec);
        return doc.read(path, ElementType.class);
    }
    
    public Attribute extractAttribute(ComponentSpec spec, String path) throws JsonProcessingException, ComponentSpecTransformationException {
        final DocumentContext doc = readSpecAsJson(spec);
        return doc.read(path, Attribute.class);
    }
    
    private <T> ComponentSpec moveItem(ComponentSpec spec, String path, int shift, TypeRef<List<T>> typeRef) throws JsonProcessingException, ComponentSpecTransformationException {
        final DocumentContext doc = readSpecAsJson(spec);

        //extract index
        final Matcher matcher = ARRAY_PATTERN.matcher(path);
        if (matcher.matches()) {
            try {
                final String arrayPath = matcher.group(1);
                final String indexString = matcher.group(2);
                if (arrayPath != null && indexString != null) {
                    final int index = Integer.parseInt(indexString);
                    final int targetIndex = index + shift;
                    
                    if (targetIndex < 0) {
                        throw new ComponentSpecTransformationException("Cannot move to index < 0: " + targetIndex);
                    }

                    //get containing list
                    final List<T> containerArray = doc.read(arrayPath, typeRef);
                    
                    if (targetIndex >= containerArray.size()) {
                        throw new ComponentSpecTransformationException("Target index out of bound: " + targetIndex);
                    }

                    // swap objects at index and targetIndex
                    final T replaced = containerArray.set(index, containerArray.get(targetIndex));
                    containerArray.set(targetIndex, replaced);

                    //replace in context
                    doc.set(arrayPath, containerArray);
                    return objectMapper.readValue(doc.jsonString(), ComponentSpec.class);
                }
            } catch (JsonProcessingException | JsonPathException | IndexOutOfBoundsException ex) {
                log.warn("Failed to move item at path: " + path, ex);
            } catch (NumberFormatException ex) {
                log.warn("Not a valid index in path:" + path);
            }
        }
        throw new ComponentSpecTransformationException(String.format("Could not move an item at %s", path));
        
    }
    
    private DocumentContext readSpecAsJson(ComponentSpec spec) throws JsonProcessingException {
        final String json = objectMapper.writeValueAsString(spec);
        final DocumentContext doc = JsonPath.parse(json, configuration);
        return doc;
    }
    
}
