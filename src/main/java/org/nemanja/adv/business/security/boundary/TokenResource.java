package org.nemanja.adv.business.security.boundary;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import org.nemanja.adv.business.administration.entity.Customer;
import org.nemanja.adv.business.error.entity.AdvSecurityException;
import org.nemanja.adv.business.security.control.TokenService;
import org.nemanja.adv.business.security.entity.AdvToken;
import org.nemanja.adv.business.security.entity.LoginUser;

@Path("tokens")
@Stateless
public class TokenResource
{

    public final static String AUTH_TOKEN = "auth_token";

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    private TokenService tokenService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid LoginUser loginUser) throws AdvSecurityException
    {

        AdvToken advToken = tokenService.addToken(loginUser.getUsername(), loginUser.getPassword());
        Customer customer = (Customer) entityManager
                .createNamedQuery("Customer.findByAdvUser")
                .setParameter("advUser", advToken.getAdvUser())
                .getSingleResult();

        String response = new JSONObject()
                .put(AUTH_TOKEN, advToken.getAuthToken())
                .put("customerId", customer.getId())
                .toString();
        return Response.status(Response.Status.CREATED).entity(response).build();

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@HeaderParam(AUTH_TOKEN) String authToken) throws AdvSecurityException
    {
        tokenService.removeToken(authToken);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
