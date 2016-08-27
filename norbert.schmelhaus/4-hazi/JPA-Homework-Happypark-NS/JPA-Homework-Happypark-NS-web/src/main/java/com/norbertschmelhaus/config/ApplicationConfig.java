package com.norbertschmelhaus.config;

import com.norbertschmelhaus.exceptionmappers.BadRequestExceptionMapper;
import com.norbertschmelhaus.exceptionmappers.GeneralExceptionMapper;
import com.norbertschmelhaus.exceptionmappers.GuestAgeIsntEnoughExcMapper;
import com.norbertschmelhaus.exceptionmappers.GuestDontUseMachineExcMapper;
import com.norbertschmelhaus.exceptionmappers.GuestHaventAnyMoneyExcMapper;
import com.norbertschmelhaus.exceptionmappers.GuestIsntActiveExceptionMapper;
import com.norbertschmelhaus.exceptionmappers.HaventFreeSeatsExceptionMapper;
import com.norbertschmelhaus.exceptionmappers.MachineInUseExceptionMapper;
import com.norbertschmelhaus.exceptionmappers.MachineNotExistInParkExcMapper;
import com.norbertschmelhaus.exceptionmappers.ParkAreaToSmallExceptionMapper;
import com.norbertschmelhaus.exceptionmappers.ParkHaventAnyMoneyExcMapper;
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
        resources.add(BadRequestExceptionMapper.class);
        resources.add(GeneralExceptionMapper.class);
        resources.add(GuestAgeIsntEnoughExcMapper.class);
        resources.add(GuestDontUseMachineExcMapper.class);
        resources.add(GuestHaventAnyMoneyExcMapper.class);
        resources.add(GuestIsntActiveExceptionMapper.class);
        resources.add(HaventFreeSeatsExceptionMapper.class);
        resources.add(MachineInUseExceptionMapper.class);
        resources.add(MachineNotExistInParkExcMapper.class);
        resources.add(ParkAreaToSmallExceptionMapper.class);
        resources.add(ParkHaventAnyMoneyExcMapper.class);
    }

}
