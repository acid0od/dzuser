package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.RGroup;
import net.odtel.dzuser.impl.repository.RGroupRepository;
import net.odtel.dzuser.impl.service.RGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("rGroupService")
public class RGroupServiceImpl implements RGroupService {

    @Autowired
    private RGroupRepository rGroupRepository;

    @Override
    public Page<RGroup> findAll (int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.ASC, "groupname"));
        return rGroupRepository.findAll(pageable);
    }
}
