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

import com.google.common.base.Strings;
import eu.clarin.cmdi.componentregistry.openapi.client.ApiClient;
import eu.clarin.cmdi.componentregistry.openapi.client.api.ItemsApi;
import eu.clarin.cmdi.componentregistry.openapi.client.api.UserApi;
import eu.clarin.cmdi.componentregistry.openapi.client.auth.HttpBasicAuth;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MultiValueMap;

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
    public ApiClient apiClient() {

        return new ApiClient() {
            @Override
            protected void updateParamsForAuth(String[] authNames, MultiValueMap<String, String> queryParams, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams) {
                super.updateParamsForAuth(authNames, queryParams, headerParams, cookieParams);
                final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated()) {
                    log.debug("Forwarding authentication to REST service: {}", authentication);
                    setAuthHeaders(authentication, headerParams);
                }
            }

            private void setAuthHeaders(Authentication authentication, HttpHeaders headerParams) {
                if (authentication.getPrincipal() instanceof User user) {
                    if (!Strings.isNullOrEmpty(user.getUsername())) {
                        final String str = user.getUsername() + ":" + clientConfig.getAuthForwardPassword();
                        headerParams.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8)));
                    }
                }
            }
        };

    }

    @Bean
    public ItemsApi itemsApi() {
        final ItemsApi api = new ItemsApi(apiClient());

        log.info("Applying configuration to API client");
        api.getApiClient().setBasePath(clientConfig.getBasePath());
        //TODO: apply addutional (optional) configuration properties – user agent...
        return api;
    }

    @Bean
    public UserApi userApi() {
        final UserApi api = new UserApi(apiClient());

        log.info("Applying configuration to API client");
        api.getApiClient().setBasePath(clientConfig.getBasePath());
        //TODO: apply addutional (optional) configuration properties – user agent...
        return api;
    }

}
