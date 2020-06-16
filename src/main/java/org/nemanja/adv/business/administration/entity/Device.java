package org.nemanja.adv.business.administration.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "Device.findAll", query = "select d from Device d"),
            @NamedQuery(name = "Device.findAllByCustomer", query = "select d from Device d where d.customer.id=:customerId"),
            @NamedQuery(name = "Device.findById", query = "select d from Device d where d.id=:id"),
            @NamedQuery(name = "Device.findBySerialNumber", query = "select d from Device d where d.serialNumber=:serialNumber")
        })
public class Device implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @NotNull
    @Size(min = 1, max = 64)
    @Column
    private String location;

    @NotNull
    @Size(min = 8, max = 8)
    @Column(unique = true)
    private String serialNumber;

    @NotNull
    @ManyToOne
    private Customer customer;

    @NotNull
    @Column
    private Boolean active;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Boolean isActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public Device(Integer id, String location, String serialNumber, Customer customer, Boolean active)
    {
        this.id = id;
        this.location = location;
        this.serialNumber = serialNumber;
        this.customer = customer;
        this.active = active;
    }

    public Device()
    {
    }

}
