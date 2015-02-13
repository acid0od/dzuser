package net.odtel.usercheck.repository;

import net.odtel.usercheck.domain.RadiusGroup;
import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.web.utils.Page;

import java.util.List;

public interface RadiusGroupRepository {

    List<RadiusGroup> findAll();

    List<RadiusGroup> findAllByUserName();

    List<RadiusGroup> findAllDistinctByGroupreplyname();

    List<String> getGroupForUser(String username);

    void updateGroupForUser(RadiusUser radiusUser);

    Page<RadiusGroup> findAll(Integer pageNumber, Integer limit);

    Page<RadiusGroup> findAllByGroupName(String groupName, Integer pageNumber, Integer limit);
}
