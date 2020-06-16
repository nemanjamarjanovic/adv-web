package org.nemanja.adv.business.administration.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.nemanja.adv.business.security.entity.AdvUser;

@XmlRootElement
@Entity
@NamedQueries(
{
    @NamedQuery(name = "Customer.findAll", query = "select c from Customer c"),
    @NamedQuery(name = "Customer.findById", query = "select c from Customer c where c.id=:id"),
    @NamedQuery(name = "Customer.findByAdvUser", query = "select c from Customer c where c.advUser=:advUser")
})
public class Customer implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @NotNull
    @Size(min = 1, max = 64)
    @Column
    private String name;

    @NotNull
    @Size(min = 1, max = 64)
    @Column
    private String location;

    @Column
    private boolean active = false;

    @OneToOne
    @NotNull
    AdvUser advUser;

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

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Customer()
    {
    }

    public Customer(Integer id, String name, String location, Boolean active, AdvUser advUser)
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.active = active;
        this.advUser = advUser;
    }

}
