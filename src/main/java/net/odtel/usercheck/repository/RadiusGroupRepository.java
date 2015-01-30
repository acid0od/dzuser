package net.odtel.usercheck.repository;

import net.odtel.usercheck.domain.RadiusGroup;

import java.util.List;

public interface RadiusGroupRepository {

    List<RadiusGroup> findAll();

    List<RadiusGroup> findAllByUserName();

    List<RadiusGroup> findAllDistinctByGroupreplyname();

}
