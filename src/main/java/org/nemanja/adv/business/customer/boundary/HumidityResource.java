//package org.nemanja.adv.business.customer.boundary;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.List;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import org.json.JSONArray;
//import org.nemanja.adv.business.Constant;
//
//@Stateless
//@Path("/humidities")
//public class HumidityResource
//{
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findAll(
//            @PathParam(value = "deviceId") Integer deviceId,
//            @QueryParam("limit") Integer limit,
//            @QueryParam("before") String before,
//            @QueryParam("after") String after,
//            @QueryParam("last") Boolean last,
//            @QueryParam("lastId") Long lastId) throws ParseException
//    {
//        List humidities;
//        if (last != null && last)
//        {
//            humidities = entityManager
//                    .createNamedQuery("Humidity.findAllForDeviceDesc")
//                    .setParameter("deviceId", deviceId)
//                    .setMaxResults(1)
//                    .getResultList();
//
////            if (((Humidity) humidities.get(0)).getId() <= lastId)
////            {
////                humidities.clear();
////            }
//        }
//        else
//        {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//            limit = (limit == null || limit <= 0) ? Constant.MAX_RESULTS : limit;
//            Long afterp = (after == null) ? System.currentTimeMillis() : sdf.parse(after).getTime();
//            Long beforep = (before == null) ? System.currentTimeMillis() - Constant.DAY : sdf.parse(before).getTime();
//
//            humidities = entityManager
//                    .createNamedQuery("Humidity.findAllBetweenTimes")
//                    .setParameter("deviceId", deviceId)
//                    .setParameter("before", beforep)
//                    .setParameter("after", afterp)
//                    .setMaxResults(limit)
//                    .getResultList();
//
//        }
//        String result = new JSONArray(humidities.toArray()).toString();
//        return Response.status(Response.Status.OK).entity(result).build();
//    }
//
////
////    @GET
////    @Path("last")
////    @Produces(MediaType.APPLICATION_JSON)
////    public Response findLast(@PathParam(value = "deviceId") Long deviceId)
////    {
////        Humidity humidity = (Humidity) entityManager
////                .createNamedQuery("Humidity.findAllForDeviceDesc")
////                .setParameter("deviceId", deviceId)
////                .getSingleResult();
////        String result = new JSONObject(humidity).toString();
////        return Response.status(Response.Status.OK).entity(result).build();
////    }
//}
