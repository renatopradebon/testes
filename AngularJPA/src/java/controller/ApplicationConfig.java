/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import app.metrics.MetricsConfigurer;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.codahale.metrics.jersey2.InstrumentedResourceMethodApplicationListener;;
import javax.inject.Inject;
import javax.ws.rs.core.Application;

/**
 *
 * @author Renato Pradebon
 */
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Inject
    private MetricsConfigurer metricsConfigurer;

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }


    @Override
    public Set<Object> getSingletons() {
        final Set<Object> instances = new HashSet<>();
        instances.add(new JacksonJsonProvider());
        instances.add(new InstrumentedResourceMethodApplicationListener(metricsConfigurer.getMetricRegistry()));
        return instances;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(app.metrics.DiagnosticFilter.class);
        resources.add(app.security.SecurityUtils.class);
        resources.add(app.security.jwt.JWTAuthenticationFilter.class);
        resources.add(controller.AccountController.class);
        resources.add(controller.EntidadeController.class);
        resources.add(controller.ListaController.class);
        resources.add(controller.LogsResource.class);
        resources.add(controller.UserController.class);
        resources.add(controller.UserJWTController.class);
    }
    
}
