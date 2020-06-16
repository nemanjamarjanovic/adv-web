package org.nemanja.adv.business.security.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import org.nemanja.adv.business.administration.entity.Customer;

@XmlRootElement
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "AdvUser.findByUsernamePassword", query = "select a from AdvUser a where a.username=:username and a.password=:password")
        })
public class AdvUser implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToOne
    private AdvRole advRole;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public AdvRole getAdvRole()
    {
        return advRole;
    }

    public void setAdvRole(AdvRole advRole)
    {
        this.advRole = advRole;
    }

    public AdvUser()
    {
    }

    public AdvUser(Integer id, String username, String password, AdvRole advRole)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.advRole = advRole;
    }

}
