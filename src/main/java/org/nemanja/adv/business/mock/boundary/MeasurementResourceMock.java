package org.nemanja.adv.business.mock.boundary;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import org.nemanja.adv.business.administration.entity.Device;
import org.nemanja.adv.business.mock.control.ArduinoRandomDataGenerator;

@Singleton
@Startup
public class MeasurementResourceMock
{

    private static final Logger LOG = Logger.getLogger(MeasurementResourceMock.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    ArduinoRandomDataGenerator arduinoRandomDataGenerator;

//    @PostConstruct
//    public void init()
//    {
//
//        WorkingMode workingMode0 = new WorkingMode(0, "NIJE ODABRAN",
//                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
//        WorkingMode workingMode1 = new WorkingMode(1, "FAZA PRVA",
//                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
//        WorkingMode workingMode2 = new WorkingMode(2, "FAZA DRUGA",
//                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
//        WorkingMode workingMode3 = new WorkingMode(3, "FAZA TRECA",
//                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
//        WorkingMode workingMode4 = new WorkingMode(4, "NEDOZVOLJENO STANJE",
//                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
//
//        entityManager.persist(workingMode0);
//        entityManager.persist(workingMode1);
//        entityManager.persist(workingMode2);
//        entityManager.persist(workingMode3);
//        entityManager.persist(workingMode4);
//
//        AdvRole arole = new AdvRole(0, AdvRole.ADMIN);
//        AdvRole crole = new AdvRole(1, AdvRole.CUSTOMER);
//        entityManager.persist(arole);
//        entityManager.persist(crole);
//
//        AdvUser auser = new AdvUser(0, "admin", "admin", arole);
//        AdvUser cuser = new AdvUser(1, "boris", "boris", crole);
//        entityManager.persist(auser);
//        entityManager.persist(cuser); 
//
//        Customer acustomer = new Customer(0, "Nemanja Marjanovic", "Banja Luka", true, auser);
//        Customer ccustomer = new Customer(1, "Boris Kremenovic", "Banja Luka", true, cuser);
//        entityManager.persist(acustomer);
//        entityManager.persist(ccustomer);
//
//        Device device = new Device(0, "Banja Luka", "AUN1BK01", ccustomer, true);
//        entityManager.persist(device);
//
//    }

    @Schedule(second = "*/2", minute = "*", hour = "*")
    public void insert()
    {
        List devices = entityManager.createNamedQuery("Device.findAll").getResultList();

        for (Object device : devices)
        {
            Device d = (Device) device;
            String data = arduinoRandomDataGenerator.getData(d.getSerialNumber());

            LOG.log(Level.INFO, "Mock send: " + data);
            ClientBuilder.newClient().target("http://localhost:8080/adv-1.0/rest/measurements")
                    .request()
                    .post(Entity.entity(data, "application/json"),
                            String.class);
        }
    }

}
