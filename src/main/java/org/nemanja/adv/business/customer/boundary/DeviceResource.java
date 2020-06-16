package org.nemanja.adv.business.customer.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nemanja.adv.business.administration.entity.Device;

@Stateless
@Path("/devices")
public class DeviceResource
{

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    DeviceStateResource deviceStateResource;

    @GET
    @Path("{deviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam(value = "deviceId") Integer deviceId)
    {
        Device device = (Device) entityManager
                .createNamedQuery("Device.findById")
                .setParameter("id", deviceId)
                .getSingleResult();

        String result = new JSONObject(device).toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam(value = "customerId") Integer customerId)
    {
        List devices = entityManager
                .createNamedQuery("Device.findAllByCustomer")
                .setParameter("customerId", customerId)
                .getResultList();

        String result = new JSONArray(devices.toArray()).toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @Path("{deviceId}/devicestates")
    public DeviceStateResource temperatures(@PathParam(value = "deviceId") Integer deviceId)
    {
        return deviceStateResource;
    }
}
