package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.FailReject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FailRejectRepository extends JpaRepository<FailReject, Long> {

    @Query("select a from FailReject a where a.userName = ?1")
    Page<FailReject> findByUserNameOrderByTimeReject (String userName, Pageable pageable);

    @Query("select a from FailReject a where a.userName like ?1")
    Page<FailReject> findByUserNameLikeOrderByTimeReject (String userName, Pageable pageable);

    @Query("select a from FailReject a where a.nasIp = ?1")
    Page<FailReject> findByNasIpOrderByTimeReject (String nasIp, Pageable pageable);

    @Query("select a from FailReject a where a.userName = ?1 and a.nasIp = ?2")
    Page<FailReject> findByUserNameAndNasIpOrderByTimeReject (String userName, String nasIp, Pageable pageable);

    @Query("select a from FailReject a where a.userName like ?1 and a.nasIp = ?2")
    Page<FailReject> findByUserNameLikeAndNasIpOrderByTimeReject (String userName, String nasIp, Pageable pageable);
    
}
