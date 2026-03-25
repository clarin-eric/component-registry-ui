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

/**
 *
 * @author twagoo
 */
public class TransformationActions {

    public static final String NOOP = "noop";
    public static final String DELETE = "delete";
    public static final String MOVE_ATTRIBUTE_DOWN = "moveAttributeDown";
    public static final String MOVE_ATTRIBUTE_UP = "moveAttributeUp";
    public static final String MOVE_ELEMENT_DOWN = "moveElementDown";
    public static final String MOVE_ELEMENT_UP = "moveElementUp";
    public static final String MOVE_COMPONENT_DOWN = "moveComponentDown";
    public static final String MOVE_COMPONENT_UP = "moveComponentUp";
    public static final String ADD_CHILD_ATTRIBUTE_TO_COMPONENT = "componentAddChildAttribute";
    public static final String ADD_CHILD_ATTRIBUTE_TO_ELEMENT = "elementAddChildAttribute";
    public static final String ADD_CHILD_ELEMENT = "addChildElement";
    public static final String ADD_CHILD_COMPONENT = "addChildComponent";
    public static final String LINK_CHILD_COMPONENT = "linkChildComponent";
    public static final String INSERT_COMPONENT = "insertComponent";
    public static final String INSERT_ELEMENT = "insertElement";

    public static final String TYPE_ATTRIBUTE = "attribute";
    public static final String TYPE_ELEMENT = "element";
    public static final String TYPE_COMPONENT = "component";
    
    public static String addAttributeActionFor(String type) {
        return switch (type.toLowerCase()) {
            case TYPE_COMPONENT ->
                ADD_CHILD_ATTRIBUTE_TO_COMPONENT;
            case TYPE_ELEMENT ->
                ADD_CHILD_ATTRIBUTE_TO_ELEMENT;
            default -> {
                throw new IllegalArgumentException("Unsupported type: " + type);
            }
        };
    }

    public static String moveUpActionFor(String type) {
        return switch (type.toLowerCase()) {
            case TYPE_COMPONENT ->
                MOVE_COMPONENT_UP;
            case TYPE_ELEMENT ->
                MOVE_ELEMENT_UP;
            case TYPE_ATTRIBUTE ->
                MOVE_ATTRIBUTE_UP;
            default -> {
                throw new IllegalArgumentException("Unsupported type: " + type);
            }
        };
    }

    public static String moveDownActionFor(String type) {
        return switch (type.toLowerCase()) {
            case TYPE_COMPONENT ->
                MOVE_COMPONENT_DOWN;
            case TYPE_ELEMENT ->
                MOVE_ELEMENT_DOWN;
            case TYPE_ATTRIBUTE ->
                MOVE_ATTRIBUTE_DOWN;
            default -> {
                throw new IllegalArgumentException("Unsupported type: " + type);
            }
        };
    }
}
