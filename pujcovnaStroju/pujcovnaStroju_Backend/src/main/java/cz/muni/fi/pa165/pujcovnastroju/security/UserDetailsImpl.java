package cz.muni.fi.pa165.pujcovnastroju.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Vojtech Schlemmer
 */
public class UserDetailsImpl extends User{

    private String salt;

    public UserDetailsImpl(String username, String password, boolean enabled, 
            boolean accountNonExpired, boolean credentialsNonExpired, 
            boolean accountNonLocked, 
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, 
                credentialsNonExpired, accountNonLocked, authorities);
    }
    
    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }
}
