package org.nemanja.adv.business.security.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class LoginUser
{

    @NotNull
    @Size(min = 1, max = 64)
    private String username;

    @NotNull
    @Size(min = 1, max = 64)
    private String password;

    public LoginUser()
    {
    }

    public LoginUser(String username, String password)
    {
        this.username = username;
        this.password = password;
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

}
