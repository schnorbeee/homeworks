package com.norbertschmelhaus.config;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@ApplicationPath("webapp")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.norbertschmelhaus.rest.CartResource.class);
        resources.add(com.norbertschmelhaus.rest.MobileInventoryResource.class);
        resources.add(com.norbertschmelhaus.rest.MobileTypeResource.class);
        resources.add(com.norbertschmelhaus.rest.UserDTOResource.class);
    }

}
