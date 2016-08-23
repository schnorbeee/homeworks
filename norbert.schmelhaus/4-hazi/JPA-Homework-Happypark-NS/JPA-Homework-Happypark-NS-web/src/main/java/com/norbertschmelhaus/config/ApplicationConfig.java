package com.norbertschmelhaus.config;

import com.norbertschmelhaus.resources.AddressResource;
import com.norbertschmelhaus.resources.GuestBookRescource;
import com.norbertschmelhaus.resources.GuestResource;
import com.norbertschmelhaus.resources.HappyparkResource;
import com.norbertschmelhaus.resources.MachineResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 */
@ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        addRestResources(resources);
        return resources;
    }

    public void addRestResources(Set<Class<?>> resources) {
        resources.add(HappyparkResource.class);
        resources.add(GuestResource.class);
        resources.add(MachineResource.class);
        resources.add(GuestBookRescource.class);
        resources.add(AddressResource.class);
    }

}
