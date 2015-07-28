package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.NasType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasTypeRepository extends JpaRepository<NasType, Integer> {

}
