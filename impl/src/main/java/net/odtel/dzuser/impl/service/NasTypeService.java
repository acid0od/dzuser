package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.NasType;
import org.springframework.data.domain.Page;

public interface NasTypeService {

    void save (NasType nasType);

    Page<NasType> findAll (int page, int size);

    NasType findById (Integer id);

    void remove (Integer id);
}
