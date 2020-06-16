package org.nemanja.adv.business.administration.boundary;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/administration")
public class AdministrationResource
{

    @Inject
    CustomersResource customerResource;

    @Inject
    DevicesResource deviceResource;

    @Inject
    WorkingModeResource workingModeResource;

    @Path("/customers")
    public CustomersResource customers()
    {
        return customerResource;
    }

    @Path("/devices")
    public DevicesResource devices()
    {
        return deviceResource;
    }

    @Path("/workingmodes")
    public WorkingModeResource workingmodes()
    {
        return workingModeResource;
    }
}
