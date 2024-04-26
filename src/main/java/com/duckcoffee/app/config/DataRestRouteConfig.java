package com.duckcoffee.app.config;

import com.duckcoffee.app.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class DataRestRouteConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origin}")
    private String theAllowedOrigins;

    @Value("${img.directory}")
    private String imgDirectory;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config ,
                                                     CorsRegistry cors) {

        config.exposeIdsFor(User.class);

        /*configure cors mapping*/
        cors.addMapping(config.getBasePath() + "/**" )
                .allowedOrigins(theAllowedOrigins);

    }

}
