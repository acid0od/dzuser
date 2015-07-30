package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.RadiusGroup;
import net.odtel.dzuser.api.model.RadiusUser;
import net.odtel.dzuser.api.web.Page;

import java.util.List;

public interface RadiusGroupRepository {

    List<RadiusGroup> findAll ();

    List<RadiusGroup> findAllByUserName (String userName);

    List<RadiusGroup> findAllDistinctByGroupreplyname ();

    List<String> getGroupForUser (String username);

    void updateGroupForUser (RadiusUser radiusUser);

    Page<RadiusGroup> findAll (Integer pageNumber, Integer limit);

    Page<RadiusGroup> findAllByGroupName (String groupName, Integer pageNumber, Integer limit);
}
