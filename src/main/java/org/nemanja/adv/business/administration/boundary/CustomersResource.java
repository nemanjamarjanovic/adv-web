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
import org.nemanja.adv.business.administration.entity.Customer;

@Stateless
@Path("/customers")
public class CustomersResource
{

    @PersistenceContext
    EntityManager entityManager;

    @GET
    @Path("{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam(value = "customerId") Integer customerId)
    {
        Customer customer = (Customer) entityManager.createNamedQuery("Customer.findById").setParameter("id", customerId).getSingleResult();
        String result = new JSONObject(customer).toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll()
    {
        List customers = entityManager.createNamedQuery("Customer.findAll").getResultList();
        String result = new JSONArray(customers.toArray()).toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Customer customer)
    {
        if (customer.getId() != null)
        {
            entityManager.merge(customer);
        }
        else
        {
            entityManager.persist(customer);
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{customerId}")
    public Response delete(@PathParam(value = "customerId") Integer customerId)
    {
        Customer customer = (Customer) entityManager.createNamedQuery("Customer.findById").setParameter("id", customerId).getSingleResult();
        entityManager.remove(customer);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
