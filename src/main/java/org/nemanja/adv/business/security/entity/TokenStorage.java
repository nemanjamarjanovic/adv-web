package org.nemanja.adv.business.security.entity;

import java.util.HashMap;
import java.util.Map;
import org.nemanja.adv.business.error.entity.AdvSecurityException;

public class TokenStorage
{

    private final Map<String, AdvToken> storage = new HashMap<>();

    public void checkAuthToken(String authToken) throws AdvSecurityException
    {
        if (!this.storage.containsKey(authToken))
        {
            //throw new AdvSecurityException(UbbSecurityException.UNAUTHORIZED);
            throw new AdvSecurityException();
        }
        this.storage.get(authToken).resetTimestamp();
    }

    public AdvToken getAdvToken(String authToken) throws AdvSecurityException
    {
        if (!this.storage.containsKey(authToken))
        {
            throw new AdvSecurityException();
        }

        return this.storage.get(authToken);

    }

    public AdvToken addAdvToken(AdvUser advUser)
    {
        AdvToken advToken = new AdvToken(advUser);
        this.storage.put(advToken.getAuthToken(), advToken);
        return advToken;
    }

    public void removeUser(String authToken)
    {
        this.storage.remove(authToken);
    }

    public void clearExpiredLoggedUsers()
    {
        for (AdvToken advToken : this.storage.values()) 
        {
            
            if (advToken.checkTimestamp())
            {
                this.storage.remove(advToken.getAuthToken());
            }
        }
    }

}
