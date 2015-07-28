package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.Location;
import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.model.NasType;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface NasService {

    Page<Nas> findAll (int page, int size);

    List<Nas> findAll ();

    List<Nas> findActive ();

    Nas findNasById (Integer Id);

    Nas save (Nas nas);

    List<NasType> findNasType ();

    List<Location> findLocation ();

    void remove (Integer idNas);

    Collection<Nas> findReject ();

    Collection<Nas> findView ();

}
