package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.RadiusUserValue;

import java.util.Set;

public interface RadiusUserValueRepository {

    RadiusUserValue createNewRecord(RadiusUserValue radiusUserValue);

    Long createOrUpdate(RadiusUserValue value);

    void remove(Long id);

    Set<Long> getSetOfUserId(String radiusUserName);
}
