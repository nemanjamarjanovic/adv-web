package org.nemanja.adv.business.security.entity;

import java.util.UUID;
import static org.nemanja.adv.business.Constant.SESSION_TIMEOUT;

public class AdvToken
{

    private final AdvUser advUser;
    private final String authToken;
    private Long timestamp;

    public AdvToken(AdvUser advUser)
    {
        this.advUser = advUser;
        this.authToken = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
    }

    public AdvUser getAdvUser()
    {
        return advUser;
    }

    public String getAuthToken()
    {
        return authToken;
    }

    public boolean checkTimestamp()
    {
        return ((System.currentTimeMillis() - this.timestamp) > SESSION_TIMEOUT);
    }

    public void resetTimestamp()
    {
        this.timestamp = System.currentTimeMillis();
    }
}
