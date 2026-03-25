/*
 * Copyright (C) 2026 CLARIN ERIC
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
import eu.clarin.cmdi.componentregistry.openapi.client.api.ItemsApi;
import eu.clarin.cmdi.componentregistry.openapi.client.model.BaseDescription;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.ITEM_STATUS_DEFAULT;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.ITEM_STATUS_QUERY_PARAM;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.ITEM_TYPE_COMPONENT;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.ITEM_TYPE_DEFAULT;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.ITEM_TYPE_PROFILE;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.ITEM_TYPE_QUERY_PARAM;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.SELECTED_ITEM_QUERY_PARAM;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.SORT_BY_DEFAULT;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.SORT_BY_QUERY_PARAM;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.SORT_DIRECTION_DEFAULT;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.SORT_DIRECTION_QUERY_PARAM;
import static eu.clarin.cmdi.componentregistry.ui.web.controller.ComponentBrowserController.TEXT_FILTER_QUERY_PARAM;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author twagoo
 */
public abstract class BaseController {

    private static final List<String> ITEM_TABLE_FIELDS = Arrays.asList(
            "name",
            "groupName",
            "domainName",
            "creatorName",
            //            "description",
            "registrationDate");

    private <T> T getFirstOrDefault(MultiValueMap<String, T> map, String key, T defaultValue) {
        return Optional.ofNullable(map.getFirst(key)).orElse(defaultValue);
    }

    protected void setCommonItemModelAttributes(MultiValueMap<String, String> params, Model model) {
        model.addAttribute("fields", ITEM_TABLE_FIELDS);
        model.addAttribute("selectedItems", params.get(SELECTED_ITEM_QUERY_PARAM));
        model.addAttribute("textFilter", params.getFirst(TEXT_FILTER_QUERY_PARAM));
        model.addAttribute("type", getFirstOrDefault(params, ITEM_TYPE_QUERY_PARAM, ITEM_TYPE_DEFAULT));
        model.addAttribute("status", params.getOrDefault(ITEM_STATUS_QUERY_PARAM, ITEM_STATUS_DEFAULT));
        model.addAttribute("sortedBy", getFirstOrDefault(params, SORT_BY_QUERY_PARAM, SORT_BY_DEFAULT));
        model.addAttribute("sortedDirection", getFirstOrDefault(params, SORT_DIRECTION_QUERY_PARAM, SORT_DIRECTION_DEFAULT));
    }

    protected List<BaseDescription> getItemsForRequest(ItemsApi api, MultiValueMap<String, String> params) {
        final String type = getFirstOrDefault(params, ITEM_TYPE_QUERY_PARAM, ITEM_TYPE_DEFAULT);
        return getItemsForRequest(api, params, type);
    }

    protected List<BaseDescription> getItemsForRequest(ItemsApi api, MultiValueMap<String, String> params, String type) {
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

    protected List<BaseDescription> filterItems(final String textFilter, List<BaseDescription> items) {
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
