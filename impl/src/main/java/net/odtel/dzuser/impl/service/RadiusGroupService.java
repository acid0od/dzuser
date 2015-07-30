package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.RadiusGroup;
import net.odtel.dzuser.api.model.RadiusUser;
import net.odtel.dzuser.api.web.Page;

import java.util.List;

public interface RadiusGroupService {

    RadiusGroup findOne (Long id);

    List<RadiusGroup> findAll ();

    List<RadiusGroup> findAllDistinctByGroupreplyname ();

    Page<RadiusGroup> findAll (Integer pageNumber, Integer limit);

    Page<RadiusGroup> findAllByGroupName (String radiusGroup, Integer pageNumber, Integer totalElementPerPage);

    List<RadiusGroup> findAllOfOrder (String someName);

    List<String> getGroupForUser (String username);

    void updateGroupForUser (RadiusUser radiusUser);

    RadiusGroup create (RadiusGroup radiusGroup);

    RadiusGroup createWithId (RadiusGroup radiusGroup);

    void update (RadiusGroup radiusGroup);

    void delete (RadiusGroup radiusGroup);

}
