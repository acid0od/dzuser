package net.odtel.usercheck.service.impl;

import net.odtel.usercheck.domain.RadiusGroup;
import net.odtel.usercheck.repository.RadiusGroupRepository;
import net.odtel.usercheck.service.RadiusGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "radiusGroupService")
@Transactional
public class RadiusGroupServiceImpl implements RadiusGroupService {

    @Autowired
    private RadiusGroupRepository radiusGroupRepository;

    @Override
    public RadiusGroup findOne(Long id) {
        return null;
    }

    @Override
    public List<RadiusGroup> findAllDistinctByGroupreplyname() {
        return radiusGroupRepository.findAllDistinctByGroupreplyname();
    }

    @Override
    public List<RadiusGroup> findAll() {
        return null;
    }

    @Override
    public List<RadiusGroup> findAllOfOrder(String someName) {
        return null;
    }

    @Override
    public RadiusGroup create(RadiusGroup radiusGroup) {
        return null;
    }

    @Override
    public RadiusGroup createWithId(RadiusGroup radiusGroup) {
        return null;
    }

    @Override
    public void update(RadiusGroup radiusGroup) {

    }

    @Override
    public void delete(RadiusGroup radiusGroup) {

    }
}
