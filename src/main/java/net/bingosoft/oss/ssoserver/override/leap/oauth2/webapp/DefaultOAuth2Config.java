package net.bingosoft.oss.ssoserver.override.leap.oauth2.webapp;

import leap.oauth2.webapp.OAuth2Exception;
import leap.web.ServerInfo;

/**
 * @author kael.
 */
public class DefaultOAuth2Config extends leap.oauth2.webapp.DefaultOAuth2Config {
    @Override
    protected String resolveUrl(String expression, ServerInfo serverInfo){
        int i = 0;
        StringBuilder source = new StringBuilder(expression);
        StringBuilder builder = new StringBuilder();
        while (source.length()>0){
            char c = source.charAt(i);
            source.deleteCharAt(i);
            if(c == '@'){
                if(source.charAt(i) == '{'){
                    source.deleteCharAt(i);
                    int index = source.indexOf("}");
                    if(-1 == index){
                        throw new OAuth2Exception("error server url expression:"+expression);
                    }
                    String exp = source.substring(0,index);
                    source.delete(0,index+1);
                    builder.append(getExpressionValue(exp,serverInfo));
                }
            }else {
                if(c != '/' || builder.length() == 0){
                    builder.append(c);
                }else if(builder.charAt(builder.length()-1)!='/'){
                    builder.append(c);
                }
            }
        }
        return builder.toString();
    }
}
