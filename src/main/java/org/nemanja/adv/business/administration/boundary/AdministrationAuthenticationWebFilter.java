package org.nemanja.adv.business.administration.boundary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.nemanja.adv.business.error.entity.AdvSecurityException;
import org.nemanja.adv.business.security.control.TokenService;

//@WebFilter(urlPatterns = "/rest/administration/*")
public class AdministrationAuthenticationWebFilter implements Filter
{

    @Inject
    private TokenService tokenService;

    private final static Logger logger = Logger.getLogger(AdministrationAuthenticationWebFilter.class.getName());
    public final static String AUTH_TOKEN = "auth_token";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String authToken = request.getHeader(AUTH_TOKEN);

        try
        {
            // security check
            if (!tokenService.getToken(authToken).getAdvUser().getAdvRole().isAdmin())
            {
                throw new AdvSecurityException();
            }
            chain.doFilter(req, res);
        }
        catch (AdvSecurityException ex)
        {
            logger.log(Level.WARNING, "UNAUTHORISED - AUTH_TOKEN: {0} MESSAGE: {1}", new Object[]
            {
                authToken, ex.getLocalizedMessage()
            });
            response.getWriter().append(new JSONObject().put("message", ex.getLocalizedMessage()).toString());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

}
