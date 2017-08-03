package net.bingosoft.oss.ssoserver;

import leap.core.annotation.Inject;
import leap.oauth2.webapp.OAuth2Config;
import leap.web.App;
import leap.web.AppListener;
import leap.web.ServerInfo;
import leap.web.api.Apis;
import leap.web.api.config.model.OAuthConfigImpl;

/**
 * 监听服务器信息解析事件，一旦服务器信息已经解析出来，则将所有rest api的服务器信息修改并解析为服务器地址
 * @author kael.
 */
public class SwaggerProcessorListener implements AppListener {
    
    protected @Inject OAuth2Config oc;
    protected @Inject Apis apis;
    
    @Override
    public void onServerInfoResolved(App app, ServerInfo serverInfo) {
        apis.getConfigurations().forEach(c -> {
            ((OAuthConfigImpl)c.getOAuthConfig()).setAuthorizationUrl(oc.getAuthorizeUrl());
            ((OAuthConfigImpl)c.getOAuthConfig()).setTokenUrl(oc.getTokenUrl());
        });
    }
}
