package net.odtel.usercheck.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.odtel.usercheck.model.User;

public interface UserManagerService {
	public User getUser(String username) throws UsernameNotFoundException ;
}
