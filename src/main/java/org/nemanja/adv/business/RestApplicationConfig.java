package org.nemanja.adv.business;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")
public class RestApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.nemanja.adv.business.administration.boundary.AdministrationResource.class);
        resources.add(org.nemanja.adv.business.administration.boundary.CustomersResource.class);
        resources.add(org.nemanja.adv.business.administration.boundary.DevicesResource.class);
        resources.add(org.nemanja.adv.business.administration.boundary.WorkingModeResource.class);
        resources.add(org.nemanja.adv.business.arduino.boundary.MeasurementResource.class);
        resources.add(org.nemanja.adv.business.customer.boundary.CustomerResource.class);
        resources.add(org.nemanja.adv.business.customer.boundary.DeviceResource.class);
        resources.add(org.nemanja.adv.business.error.boundary.AdvSecurityExceptionMapper.class);
        resources.add(org.nemanja.adv.business.error.boundary.IllegalArgumentExceptionMapper.class);
        resources.add(org.nemanja.adv.business.error.boundary.IllegalStateExceptionMapper.class);
        resources.add(org.nemanja.adv.business.error.boundary.NoResultFoundExceptionMapper.class);
        resources.add(org.nemanja.adv.business.security.boundary.TokenResource.class);
    }

}
