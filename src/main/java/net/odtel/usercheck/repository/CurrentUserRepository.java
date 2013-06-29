package net.odtel.usercheck.repository;

import java.util.List;
import net.odtel.usercheck.model.CurrentUser;


public interface CurrentUserRepository {
	public List<CurrentUser> findAll();
}
