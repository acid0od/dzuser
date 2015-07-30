package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.Location;
import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.model.NasType;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NasRepository extends JpaRepository<Nas, Integer> {

    @Query("select n from Nas n where n.check = 1")
    List<Nas> findByCheckWithMyFilters ();

    @Query("select n from Nas n where n.check = 1 and n.type = 3")
    List<Nas> findByCheckWithCisco ();

    @Query("SELECT ntype FROM NasType ntype ORDER BY ntype.name")
    List<NasType> findNasTypes () throws DataAccessException;

    @Query("SELECT location FROM Location location ORDER BY location.name")
    List<Location> findLocation () throws DataAccessException;

    @Query("SELECT DISTINCT n from Nas n, CurrentUser c where c.nasid = n.id")
    List<Nas> findByCurrentActive ();

    @Query("SELECT DISTINCT n from Nas n where description <> ''")
    List<Nas> findByFailReject ();

    @Query("SELECT DISTINCT n from Nas n where description <> ''")
    List<Nas> findByAccounting ();

}
