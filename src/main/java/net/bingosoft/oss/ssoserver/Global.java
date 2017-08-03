package net.bingosoft.oss.ssoserver;

import leap.core.annotation.Inject;
import leap.oauth2.server.OAuth2AuthzServerConfigurator;
import leap.oauth2.server.client.AuthzClient;
import leap.oauth2.server.client.AuthzClientManager;
import leap.oauth2.server.entity.AuthzClientEntity;
import leap.oauth2.webapp.OAuth2Configurator;
import leap.orm.dao.Dao;
import leap.web.App;
import leap.web.security.SecurityConfigurator;

import java.util.UUID;

/**
 * @author kael.
 */
public class Global extends App {
    
    protected @Inject OAuth2AuthzServerConfigurator oac;
    protected @Inject OAuth2Configurator owc;
    protected @Inject SecurityConfigurator sc;
    protected @Inject AuthzClientManager clientManager;
    
    @Override
    protected void init() throws Throwable {
        oac.enable();
        owc.enable().setServerUrl("@{~}");
        sc.enable().authenticateAnyRequests();
    }

    @Override
    protected void start() throws Throwable {
        initConsoleClient();
    }

    protected void initConsoleClient(){
        AuthzClient client = clientManager.loadClientById("console");
        if(null == client){
            AuthzClientEntity c = new AuthzClientEntity();
            c.setId("console");
            c.setSecret(UUID.randomUUID().toString().replace("-",""));
            c.setAccessTokenExpires(oac.config().getDefaultAccessTokenExpires());
            c.setRefreshTokenExpires(oac.config().getDefaultRefreshTokenExpires());
            c.setAllowAuthorizationCode(true);
            c.setAllowLoginToken(true);
            c.setAllowRefreshToken(true);
            c.setEnabled(true);
            c.setGrantedScope("admin");
            c.setLogoutUri("**");
            c.setLogoutUriPattern("**");
            c.setRedirectUri("**");
            c.setRedirectUriPattern("**");
            Dao.get().insert(c);

            AuthzClientEntity c1 = new AuthzClientEntity();
            c1.setId("test");
            c1.setSecret("test");
            c1.setAccessTokenExpires(oac.config().getDefaultAccessTokenExpires());
            c1.setRefreshTokenExpires(oac.config().getDefaultRefreshTokenExpires());
            c1.setAllowAuthorizationCode(true);
            c1.setAllowLoginToken(true);
            c1.setAllowRefreshToken(true);
            c1.setEnabled(true);
            c1.setGrantedScope("admin");
            c1.setLogoutUri("http://localhost:10000/sdk_tester/logout");
            c1.setLogoutUriPattern("**");
            c1.setRedirectUri("**");
            c1.setRedirectUriPattern("**");
            Dao.get().insert(c1);
        }
    }
}
