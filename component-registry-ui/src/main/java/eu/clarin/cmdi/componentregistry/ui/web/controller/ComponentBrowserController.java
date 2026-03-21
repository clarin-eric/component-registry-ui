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
package eu.clarin.cmdi.componentregistry.ui.web.controller;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ItemsApi;
import eu.clarin.cmdi.componentregistry.openapi.client.model.BaseDescription;
import eu.clarin.cmdi.componentregistry.openapi.client.model.ComponentSpec;
import static eu.clarin.cmdi.componentregistry.ui.HtmxUtils.isHtmxRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author twagoo
 */
@Controller
public class ComponentBrowserController {

    private final ItemsApi api;

    public static final String SORT_BY_QUERY_PARAM = "sortBy";
    public static final String SORT_BY_DEFAULT = "name";
    public static final String SORT_DIRECTION_QUERY_PARAM = "sortDirection";
    public static final String SORT_DIRECTION_DEFAULT = "ASC";

    public static final String ITEM_TYPE_COMPONENT = "component";
    public static final String ITEM_TYPE_PROFILE = "profile";
    public static final String ITEM_TYPE_QUERY_PARAM = "type";
    public static final String ITEM_TYPE_DEFAULT = ITEM_TYPE_PROFILE;

    public static final String TEXT_FILTER_QUERY_PARAM = "textFilter";

    public static final String ITEM_STATUS_PRODUCTION = "production";
    public static final String ITEM_STATUS_QUERY_PARAM = "status";
    public static final List<String> ITEM_STATUS_DEFAULT = ImmutableList.of(ITEM_STATUS_PRODUCTION);

    public static final String SELECTED_ITEM_QUERY_PARAM = "item";

    private static final List<String> ITEM_TABLE_FIELDS = Arrays.asList(
            "name",
            "groupName",
            "domainName",
            "creatorName",
            //            "description",
            "registrationDate");

    @Autowired
    public ComponentBrowserController(ItemsApi api) {
        this.api = api;
    }

    @GetMapping(path = {"/", "/browser"})
    public String browser(@RequestParam MultiValueMap<String, String> params, Model model) {
        setCommonModelAttributes(params, model);
        return "browser/browser";
    }

    @GetMapping(path = "/items")
    public String items(@RequestParam MultiValueMap<String, String> params, Model model) {
        List<BaseDescription> items = getItems(params);

        setCommonModelAttributes(params, model);

        //filter results
        final String textFilter = params.getFirst(TEXT_FILTER_QUERY_PARAM);
        items = filterItems(textFilter, items);

        model.addAttribute("items", items);

        return "browser/items/table";
    }

    private void setCommonModelAttributes(MultiValueMap<String, String> params, Model model) {
        model.addAttribute("fields", ITEM_TABLE_FIELDS);
        model.addAttribute("selectedItems", params.get(SELECTED_ITEM_QUERY_PARAM));
        model.addAttribute("textFilter", params.getFirst(TEXT_FILTER_QUERY_PARAM));
        model.addAttribute("type", getFirstOrDefault(params, ITEM_TYPE_QUERY_PARAM, ITEM_TYPE_DEFAULT));
        model.addAttribute("status", params.getOrDefault(ITEM_STATUS_QUERY_PARAM, ITEM_STATUS_DEFAULT));
        model.addAttribute("sortedBy", getFirstOrDefault(params, SORT_BY_QUERY_PARAM, SORT_BY_DEFAULT));
        model.addAttribute("sortedDirection", getFirstOrDefault(params, SORT_DIRECTION_QUERY_PARAM, SORT_DIRECTION_DEFAULT));
    }

    private List<BaseDescription> getItems(MultiValueMap<String, String> params) {
        final String type = getFirstOrDefault(params, ITEM_TYPE_QUERY_PARAM, ITEM_TYPE_DEFAULT);
        final String textFilter = params.getFirst(TEXT_FILTER_QUERY_PARAM);
        final List<String> status = params.getOrDefault(ITEM_STATUS_QUERY_PARAM, ITEM_STATUS_DEFAULT);
        final String sortBy = getFirstOrDefault(params, SORT_BY_QUERY_PARAM, SORT_BY_DEFAULT);
        final String sortDirection = getFirstOrDefault(params, SORT_DIRECTION_QUERY_PARAM, SORT_DIRECTION_DEFAULT);
        return switch (type) {
            case ITEM_TYPE_COMPONENT ->
                api.getItems("component", status, sortBy, sortDirection); //TODO: pass text filter
            case ITEM_TYPE_PROFILE ->
                api.getItems("profile", status, sortBy, sortDirection); //TODO: pass text filter
            default ->
                Collections.emptyList();
        };
    }

    @GetMapping(path = "/item/{id}")
    public List<ModelAndView> itemDetails(@RequestParam MultiValueMap<String, String> params, @RequestHeader Map<String, String> headers, Model model,
            @PathVariable String id) {
        //get item description from API
        final BaseDescription item = api.getItem(id);
        //get spec from API
        final ComponentSpec itemSpec = api.getItemSpec(id, MediaType.APPLICATION_JSON_VALUE);

        // set model for view
        model.addAttribute("item", item);
        model.addAttribute("spec", itemSpec);

        // add ID to params for uniform processing of partial response
        params.add(SELECTED_ITEM_QUERY_PARAM, id);

        if (isHtmxRequest(headers)) {
            // return item preview + item actions
            return ImmutableList.of(
                    partialResponse(headers, params, model, "browser/browserItemsOptions :: #selected-item-actions"),
                    partialResponse(headers, params, model, "browser/items/itemPreview :: preview"));
        } else {
            return ImmutableList.of(partialResponse(headers, params, model, "browser/items/itemPreview :: preview", true));
        }
    }

    @GetMapping(path = "/item/{id}/preview")
    public String itemPreview(Model model,
            @PathVariable String id) {
        //get item description from API
        final BaseDescription item = api.getItem(id);
        
        model.addAttribute("item", item);
        model.addAttribute("component", null);
        return "browser/items/itemPreview :: component-ref";
    }

    @GetMapping(path = "/item/{id}/specification")
    public String itemSpecification(Model model,
            @PathVariable String id) {
        //get item spec from API
        final ComponentSpec itemSpec
                = api.getItemSpec(id, MediaType.APPLICATION_JSON_VALUE);

        model.addAttribute("spec", itemSpec);
        return "browser/itemSpec";
    }

    /**
     * Partial response: main content
     *
     * @param params
     * @param headers
     * @param model
     * @return
     */
    @GetMapping(path = "/main")
    public ModelAndView main(@RequestParam MultiValueMap<String, String> params, @RequestHeader Map<String, String> headers, Model model) {
        return partialResponse(headers, params, model, "browser/browser :: #browser-main", false);
    }

    /**
     * Partial response: items container (filter + table)
     *
     * @param params
     * @param headers
     * @param model
     * @return
     */
    @GetMapping(path = "/itemsContainer")
    public ModelAndView itemsContainer(@RequestParam MultiValueMap<String, String> params, @RequestHeader Map<String, String> headers, Model model) {
        return partialResponse(headers, params, model, "browser/browserItemsContainer :: #items-container");
    }

    /**
     * Partial response: items container (filter + table)
     *
     * @param params
     * @param headers
     * @param model
     * @return
     */
    @GetMapping(path = "/itemActions")
    public ModelAndView itemActions(@RequestParam MultiValueMap<String, String> params, @RequestHeader Map<String, String> headers, Model model) {
        return partialResponse(headers, params, model, "browser/browserItemsOptions :: #selected-item-actions");
    }

    private ModelAndView partialResponse(Map<String, String> headers, MultiValueMap<String, String> params, Model model, final String fragment) {
        return partialResponse(headers, params, model, fragment, false);
    }

    /**
     * Prepares a partial response IFF the request was an HTMX request
     *
     * @param headers
     * @param params
     * @param model
     * @param fragment
     * @param forcePartial
     * @return
     * @throws RestClientResponseException
     */
    private ModelAndView partialResponse(Map<String, String> headers, MultiValueMap<String, String> params, Model model, final String fragment, boolean forcePartial) {
        if (forcePartial || isHtmxRequest(headers)) {
            setCommonModelAttributes(params, model);
            return new ModelAndView(fragment, model.asMap());
        } else {
            // not an HTMX request 
            return new ModelAndView("redirect:/", params);
        }
    }

    private <T> T getFirstOrDefault(MultiValueMap<String, T> map, String key, T defaultValue) {
        return Optional.ofNullable(map.getFirst(key)).orElse(defaultValue);
    }

    private List<BaseDescription> filterItems(final String textFilter, List<BaseDescription> items) {
        if (!Strings.isNullOrEmpty(textFilter) && !items.isEmpty()) {
            final Pattern filterPattern = Pattern.compile(Pattern.quote(textFilter), Pattern.CASE_INSENSITIVE);
            return items.stream().filter(desc -> {
                return Stream.of(desc.getId(),
                        desc.getName(),
                        desc.getDescription(),
                        desc.getGroupName(),
                        desc.getDomainName())
                        .filter(Objects::nonNull)
                        .anyMatch(val -> filterPattern.matcher(val).find());
            }).toList();
        } else {
            return items;
        }
    }

}
