package nz.govt.doc.t1m.services.credentials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * http://www.baeldung.com/spring-security-authentication-provider
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    protected MyUserDetailsService userDetailsService;

    @Autowired
    protected AuthenticationFailureBlocker authenticationFailureBlocker;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = "UNKNOWN";
        String password = null;
        if(authentication != null && authentication.getPrincipal() != null) {
            if(authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                userName = userDetails.getUsername();
                password = userDetails.getPassword();
            }
            else {
                userName = String.valueOf(authentication.getPrincipal());
                password = String.valueOf(authentication.getCredentials());
            }
        }


        boolean authenticated = false;
        try {
            //log.info("Got username and password for: " + userName + " password is " + (password != null ? "not null" : "null"));
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName, password);

            // use the credentials to try to authenticate against the third party system
            if (userDetails != null) {
                authenticated = true;
                return new UsernamePasswordAuthenticationToken(userName, password, userDetails.getAuthorities());
            }
            else {
                throw new AuthenticationServiceException("Unable to authenticate.");
            }
        }
        catch (Exception ex) {
            throw new AuthenticationServiceException("Unable to authenticate");
        }
        finally {
            authenticationFailureBlocker.trackFailedAuthentications(authenticated);
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}