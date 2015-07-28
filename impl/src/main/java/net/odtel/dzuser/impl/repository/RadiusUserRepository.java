package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.RadiusUser;
import net.odtel.dzuser.api.model.RadiusUserValue;
import net.odtel.dzuser.api.web.Page;

import java.util.List;

public interface RadiusUserRepository {

    RadiusUser findOne(Long id);

    List<RadiusUser> findAll(String login);

    Page<RadiusUser> findAllWithRange(Integer pageNumber, Integer limit);

    Page<RadiusUser> findAllOfOrderWithRange(String someLogin, Integer pageNumber, Integer limit);

    RadiusUser create(RadiusUser radiusUser);

    RadiusUser createWithId(RadiusUser radiusUser);

    void update(RadiusUser radiusUser);

    void delete(RadiusUser radiusUser);

    List<RadiusUserValue> findUserValue(String radiusUser);
}

