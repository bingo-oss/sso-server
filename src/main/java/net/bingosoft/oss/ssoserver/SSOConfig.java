package net.bingosoft.oss.ssoserver;

import leap.core.annotation.ConfigProperty;
import leap.core.annotation.Configurable;

/**
 * @author kael.
 */
@Configurable
public class SSOConfig {
    @ConfigProperty(value = "ldap.url")
    protected String ldapUrl;
    @ConfigProperty(value = "ldap.authentication", defaultValue = "simple")
    protected String ldapAuthc;

    public String getLdapUrl() {
        return ldapUrl;
    }

    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }

    public String getLdapAuthc() {
        return ldapAuthc;
    }

    public void setLdapAuthc(String ldapAuthc) {
        this.ldapAuthc = ldapAuthc;
    }
}
