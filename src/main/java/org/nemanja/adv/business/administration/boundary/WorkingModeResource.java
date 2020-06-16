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
import org.nemanja.adv.business.administration.entity.WorkingMode;

@Stateless
@Path("/workingmodes")
public class WorkingModeResource
{

    @PersistenceContext
    EntityManager entityManager;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam(value = "id") Integer id)
    {
        WorkingMode workingMode = (WorkingMode) entityManager.createNamedQuery("WorkingMode.findById").setParameter("id", id).getSingleResult();
        String result = new JSONObject(workingMode).toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll()
    {
        List workingModes = entityManager.createNamedQuery("WorkingMode.findAll").getResultList();
        String result = new JSONArray(workingModes.toArray()).toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid WorkingMode workingMode)
    {
        if (workingMode.getId() != null)
        {
            entityManager.merge(workingMode);
        }
        else
        {
            entityManager.persist(workingMode);
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam(value = "id") Integer id)
    {
        WorkingMode workingMode = (WorkingMode) entityManager.createNamedQuery("WorkingMode.findById").setParameter("id", id).getSingleResult();
        entityManager.remove(workingMode);
        return Response.status(Response.Status.NO_CONTENT).build();

    }
}
