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
package eu.clarin.cmdi.componentregistry.ui.configuration;

import com.jayway.jsonpath.spi.json.Jackson3JsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.Jackson3MappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author twagoo
 */
@Configuration
public class JsonPathConfiguration {

    @Bean
    public com.jayway.jsonpath.Configuration configuration() {
        return com.jayway.jsonpath.Configuration.builder()
                .jsonProvider(jsonProvider())
                .mappingProvider(mappingProvider())
                .build();
    }

    @Bean
    public JsonProvider jsonProvider() {
        return new Jackson3JsonProvider();
    }

    @Bean
    public MappingProvider mappingProvider() {
        return new Jackson3MappingProvider();
    }

}
