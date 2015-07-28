package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.RUser;
import net.odtel.dzuser.api.model.RUserPair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RUserRepository {

    Page<RUser> findUsernameDistinct (Pageable pageable);

    List<RUser> findByUsername (String name);

    Page<RUser> findByUsername (String name, Pageable pageable);

    Page<RUser> findByUsernameLike (String name, Pageable pageable);

    void save (RUser user);

    void saveAll (List<RUser> rUsers, String userName);

    void delete (String userName);

    void delete (Long userId);

    void deleteUserAttribute (String userName);

    void updateGroupForUser(RUserPair pair);

    void deleteAllGroup (List<String> strings, String name);

    void addAllGroup (List<String> listGroup, String name);

    void deleteUserAttribute (Long userAttrId);

    List<RUser> findUserAttributeByUsername (String name);
}
