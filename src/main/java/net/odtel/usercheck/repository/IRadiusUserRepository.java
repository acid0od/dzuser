package net.odtel.usercheck.repository;

import net.odtel.usercheck.domain.RadiusUser;

import java.util.List;

public interface IRadiusUserRepository {

    RadiusUser findOne(Long id);

    List<RadiusUser> findAll(String login);

    RadiusUser create(RadiusUser radiusUser);

    void update(RadiusUser radiusUser);

    void delete(RadiusUser radiusUser);
}
