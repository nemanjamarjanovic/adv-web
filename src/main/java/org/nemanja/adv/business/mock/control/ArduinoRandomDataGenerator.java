package org.nemanja.adv.business.mock.control;

import java.math.BigDecimal;
import java.util.Random;

public class ArduinoRandomDataGenerator
{

    public String getData(String serial)
    {
        Random random = new Random(System.currentTimeMillis());

        double t = ((int) (random.nextDouble() * 2 + 20) * 10) / 10;
        double h = ((int) (random.nextDouble() * 10 + 70) * 10) / 10;

        BigDecimal temp = new BigDecimal(t);
        BigDecimal hum = new BigDecimal(h);
        Integer i = random.nextInt(4);

        String data = serial + "-" + temp.toString() + "-" + hum.toString() + "-DA-DA-" + i + "-0";

        return data;
    }
}
