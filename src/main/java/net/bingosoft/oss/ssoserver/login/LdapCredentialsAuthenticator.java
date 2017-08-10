package net.bingosoft.oss.ssoserver.login;

import leap.core.annotation.Inject;
import leap.core.security.Credentials;
import leap.core.security.UserPrincipal;
import leap.lang.Out;
import leap.lang.logging.Log;
import leap.lang.logging.LogFactory;
import leap.web.security.SecurityConfig;
import leap.web.security.authc.AuthenticationException;
import leap.web.security.authc.credentials.CredentialsAuthenticationContext;
import leap.web.security.authc.credentials.CredentialsAuthenticator;
import leap.web.security.user.UserDetails;
import leap.web.security.user.UsernamePasswordCredentials;
import net.bingosoft.oss.ssoserver.SSOConfig;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.LdapName;
import java.util.Hashtable;

/**
 * ldap校验器
 * @author kael.
 */
public class LdapCredentialsAuthenticator implements CredentialsAuthenticator {
    
    private static final Log log = LogFactory.get(LdapCredentialsAuthenticator.class);
    protected @Inject SSOConfig config;
    protected @Inject SecurityConfig sc;
    
    @Override
    public boolean authenticate(CredentialsAuthenticationContext context, Credentials credentials,
                                Out<UserPrincipal> user) throws AuthenticationException {
        if(credentials instanceof UsernamePasswordCredentials){
            try {
                String username = authByLdap(credentials);
                UserDetails ud = sc.getUserStore().loadUserDetailsByLoginName(username);
                if(null == ud){
                    context.setErrorObj("user "+username+" not found!");
                }else {
                    user.set(ud);
                }
            } catch (NamingException e) {
                log.error(e);
                context.setErrorObj(e.getMessage());
            }
            return true;
        }
        return false;
    }
    
    protected String authByLdap(Credentials credentials) throws NamingException {
        String username = ((UsernamePasswordCredentials) credentials).getUsername();
        String password = ((UsernamePasswordCredentials) credentials).getPassword();
        DirContext ctx = null;
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, config.getLdapUrl());
        env.put(Context.SECURITY_AUTHENTICATION, config.getLdapAuthc());
        env.put(Context.SECURITY_PRINCIPAL,username);
        env.put(Context.SECURITY_CREDENTIALS,password);
        try {
            ctx = new InitialDirContext(env);
        } finally {
            if(null != ctx){
                try {
                    ctx.close();
                } catch (NamingException e) {
                    log.error(e);
                }
            }
        }
        return username;
    }
    
    
}
