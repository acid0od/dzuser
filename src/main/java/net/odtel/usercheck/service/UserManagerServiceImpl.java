package net.odtel.usercheck.service;

import java.util.HashMap;

import net.odtel.usercheck.model.User;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userManagerService")
public class UserManagerServiceImpl implements UserManagerService {

	private HashMap<String, User> users;

	public UserManagerServiceImpl() {
		users = new HashMap<String, User>();
		users.put("acid", new User("acid", "12345", "ROLE_USER"));
		users.put("root", new User("root", "nhfafhtn", "ROLE_USER, ROLE_ADMIN"));
	}

	public User getUser(String username) throws UsernameNotFoundException {
		if (!users.containsKey(username)) {
			throw new UsernameNotFoundException(username + " not found");
		}

		System.out.println("User:" + username + "Password:" + users.get(username));
		return users.get(username);
	}

}
