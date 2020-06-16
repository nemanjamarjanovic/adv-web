package org.nemanja.adv.business.security.control;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.nemanja.adv.business.error.entity.AdvSecurityException;
import org.nemanja.adv.business.security.entity.AdvToken;
import org.nemanja.adv.business.security.entity.AdvUser;
import org.nemanja.adv.business.security.entity.TokenStorage;

@Singleton
@Startup
public class TokenService
{

    private final TokenStorage tokenStorage = new TokenStorage();

    @Inject
    private AdvUserService advUserService;

    public void checkUserCredentials(String authToken) throws AdvSecurityException
    {
        cleanUpSessions();
        tokenStorage.checkAuthToken(authToken);
    }

    public AdvToken addToken(String username, String password) throws AdvSecurityException
    {
        AdvUser advUser = advUserService.getValidUser(username, password);
        AdvToken advToken = tokenStorage.addAdvToken(advUser);
        return advToken;
    }

    public AdvToken getToken(String authToken) throws AdvSecurityException
    {
        return tokenStorage.getAdvToken(authToken);
    }

    public void removeToken(String authToken) throws AdvSecurityException
    {
        tokenStorage.checkAuthToken(authToken);
        tokenStorage.removeUser(authToken);

    }

    //@Schedule(second = "*/30", minute = "*", hour = "*")
    private void cleanUpSessions()
    {
        tokenStorage.clearExpiredLoggedUsers();
    }

}
