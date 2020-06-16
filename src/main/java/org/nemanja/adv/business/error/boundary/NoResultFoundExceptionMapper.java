package org.nemanja.adv.business.error.boundary;

import javax.persistence.NoResultException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoResultFoundExceptionMapper implements ExceptionMapper<NoResultException>
{

    @Override
    public Response toResponse(NoResultException exception)
    {
        return Response
                .status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
