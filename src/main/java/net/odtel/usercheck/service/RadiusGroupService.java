package net.odtel.usercheck.service;

import net.odtel.usercheck.domain.RadiusGroup;

import java.util.List;

public interface RadiusGroupService {

    RadiusGroup findOne(Long id);

    List<RadiusGroup> findAll();

    List<RadiusGroup> findAllDistinctByGroupreplyname();

    List<RadiusGroup> findAllOfOrder(String someName);

    RadiusGroup create(RadiusGroup radiusGroup);

    RadiusGroup createWithId(RadiusGroup radiusGroup);

    void update(RadiusGroup radiusGroup);

    void delete(RadiusGroup radiusGroup);
}
