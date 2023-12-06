package com.duckcoffee.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class DataRestRouteConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config ,
                                                     CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST , HttpMethod.PATCH , HttpMethod.DELETE
                , HttpMethod.PUT};

        /*configure cors mapping*/
        cors.addMapping(config.getBasePath() + "/**" )
                .allowedOrigins(theAllowedOrigins);


    }

}
