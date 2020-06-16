package org.nemanja.adv.business.customer.entity;

import org.nemanja.adv.business.administration.entity.Device;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import org.nemanja.adv.business.administration.entity.WorkingMode;

@XmlRootElement
@Entity
@NamedQueries(
{
    @NamedQuery(
            name = "DeviceState.findAllDesc",
            query = "select ds from DeviceState ds where ds.device.id=:deviceId order by ds.createTime desc"),
    @NamedQuery(
            name = "DeviceState.findAllBetweenTimes",
            query = "select ds from DeviceState ds where ds.device.id=:deviceId and ds.createTime between :before and :after order by ds.createTime")
})
public class DeviceState implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(precision = 3, scale = 1, updatable = false)
    @NotNull
    private BigDecimal temperature;

    @Column(precision = 3, scale = 1, updatable = false)
    @NotNull
    private BigDecimal humidity;

    @ManyToOne
    @NotNull
    private Device device;

    @ManyToOne
    @NotNull
    private WorkingMode workingMode;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Past
    private Date deviceTime;

    @Column(updatable = false)
    @NotNull
    private Boolean ac;

    @Column(updatable = false)
    @NotNull
    private Boolean ventilator;

    public DeviceState()
    {
    }

    public DeviceState(BigDecimal temperature, BigDecimal humidity,
                       Device device, WorkingMode workingMode,
                       Date deviceTime, Boolean ac, Boolean ventilator)
    {
        this.temperature = temperature;
        this.humidity = humidity;
        this.device = device;
        this.workingMode = workingMode;
        this.createTime = new Date();
        this.deviceTime = deviceTime;
        this.ac = ac;
        this.ventilator = ventilator;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public Long getId()
    {
        return id;
    }

    public BigDecimal getTemperature()
    {
        return temperature;
    }

    public BigDecimal getHumidity()
    {
        return humidity;
    }

    public Device getDevice()
    {
        return device;
    }

    public WorkingMode getWorkingMode()
    {
        return workingMode;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public Date getDeviceTime()
    {
        return deviceTime;
    }

    public Boolean getAc()
    {
        return ac;
    }

    public Boolean getVentilator()
    {
        return ventilator;
    }

}
