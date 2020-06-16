package org.nemanja.adv.business.customer.boundary;

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
import org.json.JSONObject;
import org.nemanja.adv.business.administration.entity.Customer;

@Stateless
@Path("/customers")
public class CustomerResource
{

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    DeviceResource deviceResource;

    @GET
    @Path("{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam(value = "customerId") Integer customerId)
    {
        Customer customer = (Customer) entityManager.createNamedQuery("Customer.findById").setParameter("id", customerId).getSingleResult();
        String result = new JSONObject(customer).toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @Path("{customerId}/devices")
    public DeviceResource devices(@PathParam(value = "customerId") Integer customerId)
    {
        return deviceResource;
    }

}
