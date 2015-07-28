package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.RadiusGroup;
import net.odtel.dzuser.api.model.RadiusUser;
import net.odtel.dzuser.api.web.Page;
import net.odtel.dzuser.impl.repository.RadiusGroupRepository;
import net.odtel.dzuser.impl.service.RadiusGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "radiusGroupService")
@Transactional
public class RadiusGroupServiceImpl implements RadiusGroupService {

    @Autowired
    private RadiusGroupRepository radiusGroupRepository;

    @Override
    public RadiusGroup findOne (Long id) {
        return null;
    }

    @Override
    public List<RadiusGroup> findAllDistinctByGroupreplyname () {
        return radiusGroupRepository.findAllDistinctByGroupreplyname();
    }

    @Override
    public Page<RadiusGroup> findAll (Integer pageNumber, Integer limit) {
        return radiusGroupRepository.findAll(pageNumber, limit);
    }

    @Override
    public Page<RadiusGroup> findAllByGroupName (String groupName, Integer pageNumber, Integer limit) {
        return radiusGroupRepository.findAllByGroupName(groupName, pageNumber, limit);
    }

    @Override
    public List<String> getGroupForUser (String username) {
        return radiusGroupRepository.getGroupForUser(username);
    }

    @Override
    public void updateGroupForUser (RadiusUser radiusUser) {
        radiusGroupRepository.updateGroupForUser(radiusUser);
    }

    @Override
    public List<RadiusGroup> findAll () {
        return null;
    }

    @Override
    public List<RadiusGroup> findAllOfOrder (String someName) {
        return null;
    }

    @Override
    public RadiusGroup create (RadiusGroup radiusGroup) {
        return null;
    }

    @Override
    public RadiusGroup createWithId (RadiusGroup radiusGroup) {
        return null;
    }

    @Override
    public void update (RadiusGroup radiusGroup) {

    }

    @Override
    public void delete (RadiusGroup radiusGroup) {

    }
}
