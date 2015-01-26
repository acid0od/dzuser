package net.odtel.usercheck.service;

import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.repository.RadiusUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "radiusUserService")
@Transactional
public class RadiusUserService  implements  IRadiusUserService {

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
    public List<RadiusUser> findAllOfOrder(String someLogin) {
        return  repository.findAllOfOrder(someLogin);
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
