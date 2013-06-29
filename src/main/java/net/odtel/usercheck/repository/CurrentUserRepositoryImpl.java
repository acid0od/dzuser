package net.odtel.usercheck.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import net.odtel.usercheck.model.CurrentUser;

@Repository("currentUserRepository")
public class CurrentUserRepositoryImpl implements CurrentUserRepository {

	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings({ "unchecked" })
	public List<CurrentUser> findAll() {
		Query query = em.createQuery("Select j from CurrentUser j order by j.userName");
		
		List <CurrentUser> currentUsers = query.getResultList();
		
		return currentUsers;
	}

}
