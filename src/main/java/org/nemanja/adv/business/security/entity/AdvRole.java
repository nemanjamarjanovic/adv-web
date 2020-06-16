package org.nemanja.adv.business.security.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "AdvRole.findByTitle", query = "select a from AdvRole a where a.title=:title"),
            @NamedQuery(name = "AdvRole.findAll", query = "select a from AdvRole a")
        })
public class AdvRole implements Serializable
{

    private static final long serialVersionUID = 1L;
    public static final String ADMIN = "ADMIN";
    public static final String CUSTOMER = "CUSTOMER";

    @Id
    private Integer id;

    @Column
    private String title;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public boolean isAdmin()
    {
        return this.title.equals(ADMIN);
    }

    public boolean isCustomer()
    {
        return this.title.equals(CUSTOMER);
    }

    public AdvRole()
    {
    }

    public AdvRole(Integer id, String title)
    {
        this.id = id;
        this.title = title;
    }

}
