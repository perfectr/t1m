package nz.govt.doc.t1m.utils;

import nz.govt.doc.t1m.services.credentials.MyUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 */
public class SecurityUtils {

    private static final String ROLE_PREFIX = "ROLE_";

    public static GrantedAuthority createAuthority(String simpleRoleName) {
        return new SimpleGrantedAuthority(ROLE_PREFIX + simpleRoleName);
    }

    public static void assertPublicAccess() {
        // This is a bit of a funny one but wanted to have a positive statement at various places in the code like
        // controllers that public access is ok for a given controller method rather than "nothing". So this method
        // does nothing, but it does act as a statement about what the expected security level for a given method
        // needs to be.
    }

    public static void assertUserIsAdministrator() {
        assertUserHasRole(MyUserDetailsService.ROLE_ADMINISTRATOR);
    }

    public static void assertUserIsNameEditor() {
        assertUserHasRole(MyUserDetailsService.ROLE_NAME_EDITOR);
    }

    public static void assertUserIsAuthenticated() {
        assertUserHasRole(MyUserDetailsService.ROLE_USER);
    }

    public static void assertUserHasRole(String roleName) {
        if(!userHasRole(roleName)) {
            throw new RuntimeException("Not authorized");
        }
    }

    public static boolean userHasRole(String roleName) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(createAuthority(roleName));
    }

    public static String currentUsername() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
