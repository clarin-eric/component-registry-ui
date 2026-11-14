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
package eu.clarin.cmdi.componentregistry.ui;

import java.util.Map;

/**
 *
 * @author twagoo
 */
public class HtmxUtils {

    public final static String HEADER_HX_REQUEST = "HX-Request";
    
    public static boolean isHtmxRequest(Map<String, String> headers) {
        return "true".equalsIgnoreCase(headers.getOrDefault(HEADER_HX_REQUEST, "false"));
    }
}
