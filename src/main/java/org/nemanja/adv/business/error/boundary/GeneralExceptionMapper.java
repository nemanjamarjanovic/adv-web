//package org.nemanja.adv.business.error.boundary;
//
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.ExceptionMapper;
//import javax.ws.rs.ext.Provider;
//import org.json.JSONObject;
//
//@Provider
//public class GeneralExceptionMapper implements ExceptionMapper<Exception>
//{
//
//    @Override
//    public Response toResponse(Exception exception)
//    {
//        return Response
//                .status(Response.Status.INTERNAL_SERVER_ERROR)
//                .type(MediaType.APPLICATION_JSON)
//                .entity(new JSONObject().put("message", exception.getLocalizedMessage()).toString())
//                .build();
//    }
//
//}
