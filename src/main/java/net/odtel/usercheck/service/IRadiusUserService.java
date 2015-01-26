package net.odtel.usercheck.service;

import net.odtel.usercheck.domain.RadiusUser;

import java.util.List;

public interface IRadiusUserService {

    RadiusUser findOne(Long id);

    List<RadiusUser> findAll(String login);

    List<RadiusUser> findAllOfOrder(String someLogin);

    RadiusUser create(RadiusUser radiusUser);

    RadiusUser createWithId(RadiusUser radiusUser);

    void update(RadiusUser radiusUser);

    void delete(RadiusUser radiusUser);

}
