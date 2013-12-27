package cz.muni.fi.pa165.pujcovnastroju.security;

import cz.muni.fi.pa165.pujcovnastroju.dao.SystemUserDAO;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vojtech Schlemmer
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SystemUserDAO userDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user01 = null;
        try{
            user01 = userDao.getSystemUserByUsername(username);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user01.getType().name()));
        return new UserDetailsImpl(user01, true,
                true, true, true, authorities);
    }
    
}