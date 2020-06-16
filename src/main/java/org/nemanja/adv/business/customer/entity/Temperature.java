//package org.nemanja.adv.business.customer.entity;
//
//import org.nemanja.adv.business.administration.entity.Device;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.OneToOne;
//import javax.xml.bind.annotation.XmlRootElement;
//
//@XmlRootElement
//@Entity
//@NamedQueries(
//{
//    @NamedQuery(
//            name = "Temperature.findAllForDeviceDesc",
//            query = "select t from Temperature t where t.device.id=:deviceId order by t.createTime desc"),
//    @NamedQuery(
//            name = "Temperature.findAllBetweenTimes",
//            query = "select t from Temperature t where t.device.id=:deviceId and t.createTime between :before and :after order by t.createTime"),
//})
//public class Temperature implements Serializable
//{
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(precision = 3, scale = 1, updatable = false)
//    private BigDecimal temperature = new BigDecimal(BigInteger.ZERO);
//
//    @ManyToOne
//    private Device device;
//
//    @Column(updatable = false)
//    private Long createTime = System.currentTimeMillis();
//
//    public Long getId()
//    {
//        return id;
//    }
//
//    public void setId(Long id)
//    {
//        this.id = id;
//    }
//
//    public BigDecimal getTemperature()
//    {
//        return temperature;
//    }
//
//    public void setTemperature(BigDecimal temperature)
//    {
//        this.temperature = temperature;
//    }
//
//    public Long getCreateTime()
//    {
//        return createTime;
//    }
//
//    public Device getDevice()
//    {
//        return device;
//    }
//
//    public void setDevice(Device device)
//    {
//        this.device = device;
//    }
//}
