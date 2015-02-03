package net.odtel.usercheck.service;

import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.domain.RadiusUserValue;
import net.odtel.usercheck.web.utils.Page;

import java.util.List;

public interface RadiusUserService {

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
