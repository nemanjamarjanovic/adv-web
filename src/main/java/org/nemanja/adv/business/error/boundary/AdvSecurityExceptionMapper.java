package org.nemanja.adv.business.error.boundary;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.nemanja.adv.business.error.entity.AdvSecurityException;

@Provider
public class AdvSecurityExceptionMapper implements ExceptionMapper<AdvSecurityException>
{

    @Override
    public Response toResponse(AdvSecurityException exception)
    {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
