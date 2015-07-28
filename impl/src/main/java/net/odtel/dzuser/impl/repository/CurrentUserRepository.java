package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.CurrentUser;
import net.odtel.dzuser.api.model.Nas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentUserRepository extends JpaRepository<CurrentUser, String> {

    @Query("select u from CurrentUser u where u.userName = ?1")
    Page<CurrentUser> findByUserName (String userName, Pageable pageable);

    @Query("select u from CurrentUser u where u.userName like ?1")
    Page<CurrentUser> findByUserNameLike (String userName, Pageable pageable);

    @Query("select u from CurrentUser u where u.nasid = ?1")
    Page<CurrentUser> findByNas (Nas nas, Pageable pageable);

    @Query("select u from CurrentUser u where u.userName = ?1 and u.nasid = ?2")
    Page<CurrentUser> findByUserNameAndNas (String userName, Nas nas, Pageable pageable);

    @Query("select u from CurrentUser u where u.userName like ?1 and u.nasid = ?2")
    Page<CurrentUser> findByUserLikeNameAndNas (String userName, Nas nasid, Pageable pageable);

}
