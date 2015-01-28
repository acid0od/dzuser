package net.odtel.usercheck.service.impl;

import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.repository.RadiusUserRepository;
import net.odtel.usercheck.repository.impl.RadiusUserRepositoryImpl;
import net.odtel.usercheck.service.RadiusUserService;
import net.odtel.usercheck.web.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "radiusUserService")
@Transactional
public class RadiusUserServiceImpl implements RadiusUserService {

    @Autowired
    private RadiusUserRepository repository;

    @Override
    public RadiusUser findOne(Long id) {
        return  repository.findOne(id);
    }

    @Override
    public List<RadiusUser> findAll(String login) {
        return repository.findAll(login);
    }

    @Override
    public Page<RadiusUser> findAllWithRange(Integer pageNumber, Integer limit) {
        return  repository.findAllWithRange(pageNumber, limit);
    }

    @Override
    public Page<RadiusUser> findAllOfOrderWithRange(String someLogin, Integer pageNumber, Integer limit) {
        return  repository.findAllOfOrderWithRange(someLogin, pageNumber, limit);
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
        repository.update(radiusUser);
    }

    @Override
    public void delete(RadiusUser radiusUser) {
        repository.delete(radiusUser);
    }
}
