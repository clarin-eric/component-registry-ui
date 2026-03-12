/*
 * Copyright (C) 2024 twagoo
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

import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ItemsApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author CLARIN ERIC
 */
@Configuration
@Slf4j
public class ApiClientConfiguration {

    @Autowired
    ApiClientConfigurationProperties clientConfig;

    @Bean
    public ItemsApi itemsApi() {
        final ItemsApi api = new ItemsApi();

        log.info("Applying configuration to API client");
        api.getApiClient().setBasePath(clientConfig.getBasePath());
        //TODO: apply addutional (optional) configuration properties – username, password, user agent...
        return api;
    }

    @Bean
    public ApiClient apiClient() {
        return itemsApi().getApiClient();
    }

}
