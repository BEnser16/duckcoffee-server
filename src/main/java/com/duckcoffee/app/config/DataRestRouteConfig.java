package com.duckcoffee.app.config;

import com.duckcoffee.app.entity.User;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class DataRestRouteConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config ,
                                                     CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST , HttpMethod.PATCH , HttpMethod.DELETE
                , HttpMethod.PUT};

        config.exposeIdsFor(User.class);



        /*configure cors mapping*/
        cors.addMapping(config.getBasePath() + "/**" )
                .allowedOrigins(theAllowedOrigins);


    }

}
