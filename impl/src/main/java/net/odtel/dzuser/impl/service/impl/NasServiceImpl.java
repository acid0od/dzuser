package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.Location;
import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.model.NasType;
import net.odtel.dzuser.impl.repository.NasRepository;
import net.odtel.dzuser.impl.service.NasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("nasService")
public class NasServiceImpl implements NasService {
    private static Logger LOGGER = LoggerFactory.getLogger(NasServiceImpl.class);

    @Autowired
    private NasRepository nasRepository;

    @Override
    public Page<Nas> findAll (int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.ASC, "description"));
        return nasRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Nas> findAll () {
        return nasRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Nas save (Nas nas) {
        return nasRepository.save(nas);
    }

    @Transactional(readOnly = true)
    public Nas findNasById (Integer id) {
        return nasRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<NasType> findNasType () {
        return nasRepository.findNasTypes();
    }

    @Transactional(readOnly = true)
    public List<Location> findLocation () {
        return nasRepository.findLocation();

    }

    @Transactional(readOnly = false)
    public void remove (Integer idNas) {
        Nas nas = nasRepository.findOne(idNas);
        if (nas != null) {
            LOGGER.debug("Delete Nas: {}", nas.getDescription());
            nasRepository.delete(nasRepository.findOne(idNas));
        }
    }

    @Transactional(readOnly = false)
    public List<Nas> findActive () {
        return nasRepository.findByCurrentActive();
    }

    @Transactional(readOnly = false)
    public List<Nas> findReject () {
        return nasRepository.findByFailReject();
    }

    @Transactional(readOnly = false)
    public List<Nas> findView () {
        return nasRepository.findByAccounting();
    }

}
