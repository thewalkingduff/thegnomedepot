package com.devduffy.gnomedepot.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.entity.UserRole;
import com.devduffy.gnomedepot.repository.UserRepository;
import com.devduffy.gnomedepot.repository.UserRoleRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	public static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        User user = userRepository.findByUsername(username);
		System.out.println("Testing");

		if (user == null) {
			throw new UsernameNotFoundException("Username '" + username + "' not found in database");
		}

		List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());

		boolean accountIsEnabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, springRoles);
	}

	private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserRole role : userRoles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

}
