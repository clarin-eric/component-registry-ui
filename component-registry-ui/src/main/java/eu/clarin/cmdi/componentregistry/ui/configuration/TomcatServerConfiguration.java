/*
 * Copyright (C) 2025 CLARIN ERIC
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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * Tomcat server configuration customization. NOTE: Reading and setting of
 * maxParameterCount via should be available 'natively' in Spring Boot as of
 * v3.5.0. See
 * {@link https://github.com/spring-projects/spring-boot/pull/43286}.
 *
 * @author twagoo
 */
@Component
public class TomcatServerConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Value("${server.tomcat.max-parameter-count:20000}")
    Integer maxParamCount;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {

        factory.addConnectorCustomizers(connector -> {
            if (maxParamCount != null) {
                connector.setMaxParameterCount(maxParamCount);
            }
        });
    }

}
