package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.RGroup;
import org.springframework.data.domain.Page;

public interface RGroupService {

    Page<RGroup> findAll (int page, int size);

}
