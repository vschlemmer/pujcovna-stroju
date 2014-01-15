package cz.muni.fi.pa165.pujcovnastroju.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.pujcovnastroju.dao.SystemUserDAO;
import cz.muni.fi.pa165.pujcovnastroju.entity.SystemUser;
import cz.muni.fi.pa165.pujcovnastroju.entity.UserTypeEnum;

/**
 * 
 * @author Vojtech Schlemmer
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SystemUserDAO userDao;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		SystemUser user01 = null;

		try {
			user01 = userDao.getSystemUserByUsername(username);
			if (user01 == null && username.equals("admin")) {
				user01 = new SystemUser();
				user01.setFirstName("admin");
				user01.setLastName("admin");
				user01.setUsername("admin");
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode("admin");
				user01.setPassword(encodedPassword);
				user01.setType(UserTypeEnum.ADMINISTRATOR);
				user01 = userDao.create(user01);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user01.getType().name()));
		return new UserDetailsImpl(user01, true, true, true, true, authorities);
	}

}