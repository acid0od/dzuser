package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.Accounting;
import net.odtel.dzuser.api.model.Nas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingRepository extends JpaRepository<Accounting, Long> {

    @Query("select a from Accounting a where a.user = ?1")
    Page<Accounting> findByUserNameOrderByTimeStart (String userName, Pageable pageable);

    @Query("select a from Accounting a where a.user = ?1 and a.nasid = ?2")
    Page<Accounting> findByUserNameAndNasIdOrderByTimeStart (String userName, Nas nas, Pageable pageable);

    @Query("select a from Accounting a where a.nasid = ?1")
    Page<Accounting> findByNasIdOrderByTimeStart (Nas nas, Pageable pageable);

    @Query("select a from Accounting a where a.user like ?1")
    Page<Accounting> findByUserNameLikeOrderByTimeStart (String string, Pageable pageable);

    @Query("select a from Accounting a where a.user like ?1 and a.nasid = ?2")
    Page<Accounting> findByUserNameLikeAndNasIdOrderByTimeStart (String concat, Nas nasid, Pageable pageable);

}
