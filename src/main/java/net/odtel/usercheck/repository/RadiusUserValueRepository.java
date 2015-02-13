package net.odtel.usercheck.repository;

import net.odtel.usercheck.domain.RadiusUserValue;

import java.util.Set;

public interface RadiusUserValueRepository {

    RadiusUserValue createNewRecord(RadiusUserValue radiusUserValue);

    Long createOrUpdate(RadiusUserValue value);

    void remove(Long id);

    Set<Long> getSetOfUserId(String radiusUserName);
}
