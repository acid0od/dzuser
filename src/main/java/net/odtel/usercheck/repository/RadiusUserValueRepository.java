package net.odtel.usercheck.repository;

import net.odtel.usercheck.domain.RadiusUserValue;

public interface RadiusUserValueRepository {
    RadiusUserValue createNewRecord(RadiusUserValue radiusUserValue);
    Long createOrUpdate(RadiusUserValue value);
}
