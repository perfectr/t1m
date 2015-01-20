package nz.govt.doc.t1m.services.credentials;

import nz.govt.doc.t1m.domain.person.PersonCredentials;
import nz.govt.doc.t1m.domain.person.PersonEntity;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.person.PersonService;
import nz.govt.doc.t1m.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 */
@Component
public class MyUserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);


    public static final String ROLE_USER          = "USER";
    public static final String ROLE_NAME_EDITOR   = "NAME_EDITOR";
    public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";

    @Autowired
    protected PersonService personService;

    @Autowired
    protected PersonCredentialsService personCredentialsService;

    @Autowired
    protected PasswordEncoder passwordEncoder;


    /**
     */
    public UserDetails loadUserByUsername(String userName, String password) throws AuthenticationException {

        Response<PersonEntity> person = personService.findPersonByEmail(userName);
        if(person != null) {
            PersonCredentials personCredentials = personCredentialsService.findByPersonId(person.getModel().getPersonId());
            if(personCredentials != null) {
                boolean authenticated = password != null && passwordEncoder.matches(password, personCredentials.getPasswordHash());

                if(authenticated) {

                    Set<GrantedAuthority> authorities = new HashSet<>();
                    authorities.add(SecurityUtils.createAuthority(ROLE_USER));

                    //if (restrictedPerson.isNameEditor()) {
                        authorities.add(SecurityUtils.createAuthority(ROLE_NAME_EDITOR));
                    //}

                    //if (restrictedPerson.isAdministrator()) {
                        authorities.add(SecurityUtils.createAuthority(ROLE_ADMINISTRATOR));
                    //}

                    UserDetails userDetails = new User(person.getModel().getEmailAddress(), personCredentials.getPasswordHash(), authorities);
                    return userDetails;
                }
                else {
                    throw new BadCredentialsException("Invalid credentials");
                }
            }
            else {
                throw new UsernameNotFoundException("Unable to find credentials for " + userName);
            }
        }
        else {
            throw new UsernameNotFoundException("Unable to find username for " + userName);
        }
    }


}
