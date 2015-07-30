package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.RGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RGroupRepository extends JpaRepository<RGroup, Long> {

}
