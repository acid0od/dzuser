package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.Location;
import net.odtel.dzuser.impl.repository.LocationRepository;
import net.odtel.dzuser.impl.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll () {
        return locationRepository.findAll();
    }

    @Override
    public Page<Location> findAll (int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.ASC, "name"));
        return locationRepository.findAll(pageable);
    }

    public Location findById (Integer id) {
        return locationRepository.findOne(id);
    }

    public void save (Location location) {
        locationRepository.save(location);
    }

    public void delete (Integer id) {
        locationRepository.delete(id);
    }

}
