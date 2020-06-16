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
//import javax.xml.bind.annotation.XmlRootElement;
//
//@XmlRootElement
//@Entity
//@NamedQueries(
//{
//    @NamedQuery(
//            name = "Humidity.findAllForDeviceDesc",
//            query = "select h from Humidity h where h.device.id=:deviceId order by h.createTime desc"),
//    @NamedQuery(
//            name = "Humidity.findAllBetweenTimes",
//            query = "select h from Humidity h where h.device.id=:deviceId and h.createTime between :before and :after order by h.createTime"),
//})
//public class Humidity implements Serializable
//{
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(precision = 3, scale = 1, updatable = false)
//    private BigDecimal humidity = new BigDecimal(BigInteger.ZERO);
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
//    public BigDecimal getHumidity()
//    {
//        return humidity;
//    }
//
//    public void setHumidity(BigDecimal humidity)
//    {
//        this.humidity = humidity;
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
//
//}
