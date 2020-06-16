package rest;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AdvRestTes
{
    @Test
    public void arduinoMock()
    {
        String data = new ArduinoRandomDataGenerator().getData("AU1BK01");
        
        WebTarget wt = ClientBuilder.newClient().target("http://localhost:8080/adv-1.0/rest/measurements");
        Response response = wt.request().post(Entity.entity(data, "application/json"), Response.class);
        assertEquals(201, response.getStatus());
    }
}
