package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.DialupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialupUserRepository extends JpaRepository<DialupUser, Integer> {

    @Query("select a from DialupUser a where a.name = ?1")
    List<DialupUser> findByName (String name);

}
