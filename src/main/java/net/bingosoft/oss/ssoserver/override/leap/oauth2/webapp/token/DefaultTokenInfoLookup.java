package net.bingosoft.oss.ssoserver.override.leap.oauth2.webapp.token;

import leap.core.annotation.Inject;
import leap.oauth2.server.token.AuthzAccessToken;
import leap.oauth2.server.token.AuthzTokenManager;
import leap.oauth2.webapp.token.SimpleTokenInfo;
import leap.oauth2.webapp.token.TokenInfo;
import leap.oauth2.webapp.token.TokenInfoLookup;

/**
 * @author kael.
 */
public class DefaultTokenInfoLookup implements TokenInfoLookup {

    protected @Inject AuthzTokenManager tokenManager;
    
    @Override
    public TokenInfo lookupByAccessToken(String at) {
        AuthzAccessToken accessToken = tokenManager.loadAccessToken(at);
        if(null == accessToken){
            return null;
        }
        return createTokenInfo(accessToken);
    }

    protected TokenInfo createTokenInfo(AuthzAccessToken accessToken) {
        SimpleTokenInfo details = new SimpleTokenInfo();

        details.setClientId(accessToken.getClientId());
        details.setUserId(accessToken.getUserId());
        details.setCreated(accessToken.getCreated());
        details.setExpiresIn(accessToken.getExpiresInFormNow());
        details.setScope(accessToken.getScope());
        return details;
    }
}
