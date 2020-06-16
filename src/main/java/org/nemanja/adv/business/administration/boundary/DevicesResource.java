package org.nemanja.adv.business.administration.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class DevicesResource
{

    @PersistenceContext
    EntityManager entityManager;

    @GET
    @Path("{deviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam(value = "deviceId") Integer deviceId)
    {
        Device device = (Device) entityManager.createNamedQuery("Device.findById").setParameter("id", deviceId).getSingleResult();
        String result = new JSONObject(device).toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll()
    {
        List devices = entityManager.createNamedQuery("Device.findAll").getResultList();
        String result = new JSONArray(devices.toArray()).toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Device device)
    {
        if (device.getId() != null)
        {
            entityManager.merge(device);
        }
        else
        {
            entityManager.persist(device);
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{deviceId}")
    public Response delete(@PathParam(value = "deviceId") Integer deviceId)
    {
        Device device = (Device) entityManager.createNamedQuery("Device.findById").setParameter("id", deviceId).getSingleResult();
        entityManager.remove(device);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
