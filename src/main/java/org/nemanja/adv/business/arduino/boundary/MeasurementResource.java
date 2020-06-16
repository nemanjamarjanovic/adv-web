package org.nemanja.adv.business.arduino.boundary;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.nemanja.adv.business.administration.entity.Device;
import org.nemanja.adv.business.arduino.entity.Measurement;
import org.nemanja.adv.business.administration.entity.WorkingMode;
import org.nemanja.adv.business.customer.entity.DeviceState;

@Stateless
@Path("/measurements")
public class MeasurementResource
{

    //private static final Logger LOG = Logger.getLogger(MeasurementResource.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final String input)
    {
        // LOG.log(Level.INFO, "Insert: " + input);

        //Ubaciti String2Measurement convertor
        String values[] = input.trim().split("-");
        for (String value : values)
        {
            value = value.trim();
        }

        Device device = (Device) entityManager
                .createNamedQuery("Device.findBySerialNumber")
                .setParameter("serialNumber", values[0])
                .getSingleResult();

        WorkingMode workingMode = (WorkingMode) entityManager
                .createNamedQuery("WorkingMode.findById")
                .setParameter("id", Integer.parseInt(values[5]))
                .getSingleResult();

        Measurement measurement = new Measurement(input, device);
        entityManager.persist(measurement);

        Date deviceTime;
        try
        {
            deviceTime = new SimpleDateFormat("").parse(values[6]);
        }
        catch (ParseException ex)
        {
            deviceTime = new Date(0);
        }

        DeviceState deviceState = new DeviceState(new BigDecimal(values[1]), new BigDecimal(values[2]),
                device, workingMode, deviceTime, values[3].equals("DA"), values[4].equals("DA"));
        entityManager.persist(deviceState);

//        measurement.setDevice(device);
//        measurement.setOriginalData(input.trim());
//        measurement.setTemperature(new BigDecimal(values[1]));
//        measurement.setHumidity(new BigDecimal(values[2]));
//        measurement.setAc(values[3].equals("DA"));
//        measurement.setVentilator(values[4].equals("DA"));
//        measurement.setWorkingMode(Integer.parseInt(values[5]));
//        measurement.setDeviceTime(values[6]);
//        device.setLastUpdateTime(measurement.getCreateTime());
//        device.setActive(true);
//        device.setWorkingMode(workingMode);
//        entityManager.merge(device);
//        Temperature temperature = new Temperature();
//        temperature.setDevice(device);
//        temperature.setTemperature(measurement.getTemperature());
//        entityManager.persist(temperature);
//
//        Humidity humidity = new Humidity();
//        humidity.setDevice(device);
//        humidity.setHumidity(measurement.getHumidity());
//        entityManager.persist(humidity);
        return Response.status(Response.Status.CREATED).build();
    }

}
