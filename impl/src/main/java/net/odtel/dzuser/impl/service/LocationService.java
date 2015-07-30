package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.Location;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocationService {

    List<Location> findAll ();

    Page<Location> findAll (int page, int size);

    Location findById (Integer id);

    void save (Location location);

    void delete (Integer id);
}
