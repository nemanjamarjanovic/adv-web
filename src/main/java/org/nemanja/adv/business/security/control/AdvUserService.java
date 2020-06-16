package org.nemanja.adv.business.security.control;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.nemanja.adv.business.error.entity.AdvSecurityException;
import org.nemanja.adv.business.security.entity.AdvUser;

@Stateless
public class AdvUserService
{

    @PersistenceContext()
    EntityManager entityManager;

    public AdvUser getValidUser(String username, String password) throws AdvSecurityException
    {

        List users = entityManager
                .createNamedQuery("AdvUser.findByUsernamePassword")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        if (users.isEmpty() || users.size() > 1 || users.get(0) == null)
        {
            throw new AdvSecurityException();
        }
        return (AdvUser) users.get(0);
    }
}
