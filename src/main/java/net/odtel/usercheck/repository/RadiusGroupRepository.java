package net.odtel.usercheck.repository;

import net.odtel.usercheck.domain.RadiusGroup;
import net.odtel.usercheck.domain.RadiusUser;

import java.util.List;

public interface RadiusGroupRepository {

    List<RadiusGroup> findAll();

    List<RadiusGroup> findAllByUserName();

    List<RadiusGroup> findAllDistinctByGroupreplyname();

    List<String> getGroupForUser(String username);

    void updateGroupForUser(RadiusUser radiusUser);

}
