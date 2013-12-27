package cz.muni.fi.pa165.pujcovnastroju.security;

import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Vojtech Schlemmer
 */
public class UserDetailsImpl extends User{

    private String salt;

    public UserDetailsImpl(SystemUser user, boolean enabled, 
            boolean accountNonExpired, boolean credentialsNonExpired, 
            boolean accountNonLocked, 
            Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), enabled, accountNonExpired, 
                credentialsNonExpired, accountNonLocked, authorities);
    }
	
    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }
}
