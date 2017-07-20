/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.datapar.controller;

import java.util.Set;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;
import javax.mvc.engine.ViewEngine;

/**
 *
 * @author Renato Pradebon
 */
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }


    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ViewEngine.VIEW_FOLDER, "/view");
        return props;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(py.com.datapar.controller.CustomerController.class);
        resources.add(py.com.datapar.controller.ProductController.class);
        resources.add(py.com.datapar.controller.ProductOrderController.class);
        resources.add(py.com.datapar.controller.util.CustomParamConverterProvider.class);
    }
    
}
