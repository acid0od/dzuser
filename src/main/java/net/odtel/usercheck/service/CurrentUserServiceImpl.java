package net.odtel.usercheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.odtel.usercheck.model.CurrentUser;
import net.odtel.usercheck.repository.CurrentUserRepository;

@Service("currentUserService")
public class CurrentUserServiceImpl implements CurrentUserService {
	
	@Autowired
	private CurrentUserRepository currentUserRepository;
	
	public List<CurrentUser> findAll() {
		return currentUserRepository.findAll();
	}
	

}
