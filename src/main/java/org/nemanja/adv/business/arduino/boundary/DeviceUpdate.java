package org.nemanja.adv.business.arduino.boundary;

//package org.nemanja.adv.business.arduino.boundary.device;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ejb.Singleton;
//import javax.inject.Inject;
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//
//import org.nemanja.adv.business.security.boundary.TokenService;
//import org.nemanja.adv.business.security.entity.AdvToken;
//
//@ServerEndpoint("/deviceupdate/{token}")
//@Singleton
//public class DeviceUpdate
//{
//
//    @Inject
//    TokenService tokenService;
//
//    private static final Logger LOG = Logger.getLogger(DeviceUpdate.class.getName());
////    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
////    private static final Set<Session> confirmedPeers = Collections.synchronizedSet(new HashSet<Session>());
////    
//    private static final Map<Long, Session> registeredUsers = Collections.synchronizedMap(new HashMap<Long, Session>());
//
//    @OnMessage
//    public void onMessage(String message, @PathParam("token") String token)
//    {
//    }
//
//    @OnOpen
//    public void onOpen(Session session, @PathParam("token") String token)
//    {
//        AdvToken advToken = tokenService.getToken(token);
//        if (advToken != null)
//        {
//            registeredUsers.put(advToken.getCustomerId(), session);
//            LOG.log(Level.INFO, advToken.getAuthToken() + " " + advToken.getCustomerId());
//        }
//    }
//
//    @OnClose
//    public void onClose(Session session, @PathParam("token") String token)
//    {
//        
//    }
//
//    // @Schedule(second = "*/5", minute = "*", hour = "*")
//    public void broadcast(Long customerId) throws IOException
//    {
//        LOG.log(Level.INFO, "START UPDATE");
//
//        for (Long key : registeredUsers.keySet())
//        {
//            if (key.equals(customerId))
//            {
//                registeredUsers.get(key).getBasicRemote().sendText("UPDATE!");
//                LOG.log(Level.INFO, "CUSTOMER UPDATE " + key);
//            }
//        }
//        LOG.log(Level.INFO, "END UPDATE");
//    }
//}
