package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.NasType;
import net.odtel.dzuser.impl.repository.NasTypeRepository;
import net.odtel.dzuser.impl.service.NasTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service("nasTypeService")
public class NasTypeServiceImpl implements NasTypeService {

    @Autowired
    private NasTypeRepository nasTypeRepository;

    @Override
    public Page<NasType> findAll (int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.ASC, "name"));
        return nasTypeRepository.findAll(pageable);
    }

    @Override
    public void save (NasType nasType) {
        nasTypeRepository.save(nasType);
    }

    @Override
    public NasType findById (Integer id) {
        return nasTypeRepository.findOne(id);
    }

    @Override
    public void remove (Integer id) {
        nasTypeRepository.delete(id);
    }
}
