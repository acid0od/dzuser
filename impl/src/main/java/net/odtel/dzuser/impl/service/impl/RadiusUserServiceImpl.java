package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.RadiusUser;
import net.odtel.dzuser.api.model.RadiusUserValue;
import net.odtel.dzuser.api.web.Page;
import net.odtel.dzuser.impl.repository.RadiusUserRepository;
import net.odtel.dzuser.impl.repository.RadiusUserValueRepository;
import net.odtel.dzuser.impl.service.RadiusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service(value = "radiusUserService")
@Transactional
public class RadiusUserServiceImpl implements RadiusUserService {

    @Autowired
    private RadiusUserRepository repository;

    @Autowired
    private RadiusUserValueRepository radiusUserValueRepository;

    @Override
    public RadiusUser findOne(Long id) {

        return repository.findOne(id);
    }

    @Override
    public List<RadiusUser> findAll(String login) {
        return repository.findAll(login);
    }

    @Override
    public Page<RadiusUser> findAllWithRange(Integer pageNumber, Integer limit) {
        return repository.findAllWithRange(pageNumber, limit);
    }

    @Override
    public Page<RadiusUser> findAllOfOrderWithRange(String someLogin, Integer pageNumber, Integer limit) {
        return repository.findAllOfOrderWithRange(someLogin, pageNumber, limit);
    }

    @Override
    public RadiusUser create(RadiusUser radiusUser) {
        return repository.create(radiusUser);
    }

    @Override
    public RadiusUser createWithId(RadiusUser radiusUser) {
        return repository.createWithId(radiusUser);
    }

    @Override
    public void update(RadiusUser radiusUser) {
        if (radiusUser.getRadiusUserValues() != null) {
            List<RadiusUserValue> values = radiusUser.getRadiusUserValues();
            Set<Long> radiusUserLongSet = radiusUserValueRepository.getSetOfUserId(radiusUser.getUsername());
            for (RadiusUserValue value : values) {
                if (value.getId() != null && value.getId() > 0) {
                    radiusUserLongSet.remove(value.getId());
                }
                value.setUsername(radiusUser.getUsername());
                radiusUserValueRepository.createOrUpdate(value);
            }
            if (radiusUserLongSet.size() > 0) {
                for (Long l : radiusUserLongSet) {
                    radiusUserValueRepository.remove(l);
                }
            }
        }

        repository.update(radiusUser);

    }

    @Override
    public void delete(RadiusUser radiusUser) {
        repository.delete(radiusUser);
    }

    @Override
    public List<RadiusUserValue> findUserValue(String radiusUser) {
        return repository.findUserValue(radiusUser);
    }
}
