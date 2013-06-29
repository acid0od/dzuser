package net.odtel.usercheck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserManagerService userManager;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("HWE+++++" + "username" + "+++++" + userManager.getUser(username));
		return userManager.getUser(username);
	}

}
