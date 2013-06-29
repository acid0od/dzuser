package net.odtel.usercheck.service;

import java.util.List;

import net.odtel.usercheck.model.CurrentUser;

public interface CurrentUserService {
	public List<CurrentUser> findAll();
}
