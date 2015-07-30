package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.RGroupReply;
import net.odtel.dzuser.api.model.RUser;
import net.odtel.dzuser.api.model.RUserPair;
import net.odtel.dzuser.api.model.RUserPairFactory;
import net.odtel.dzuser.api.model.RadiusAttribute;
import net.odtel.dzuser.api.model.RadiusOperation;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.repository.RGroupReplyRepository;
import net.odtel.dzuser.impl.repository.RUserRepository;
import net.odtel.dzuser.impl.service.RUserService;
import net.odtel.dzuser.impl.util.SearchPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("rUserService")
public class RUserServiceImpl implements RUserService {

    @Autowired
    private RUserRepository rUserRepository;

    @Autowired
    private RGroupReplyRepository rGroupReplyRepository;

    @Override
    public Page<RUser> findAll (int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.ASC, "username"));
        return rUserRepository.findUsernameDistinct(pageable);
    }

    @Override
    public Page<RUser> findByUserName (SearchRequestModel searchRequest, int pageNum, int defaultPageSize) {

        Pageable pageable = new PageRequest(pageNum, defaultPageSize, new Sort(Sort.Direction.ASC, "username"));

        if (searchRequest == null) {
            return rUserRepository.findUsernameDistinct(pageable);
        }

        SearchPattern searchPattern = new SearchPattern(searchRequest.getUserName());

        switch (searchPattern.getPar()) {
            case SearchPattern.FULL_NAME: {
                return rUserRepository.findByUsername(searchPattern.getSearchPattern(), pageable);
            }

            case SearchPattern.PARTIAL_NAME: {
                return rUserRepository.findByUsernameLike(searchPattern.getSearchPattern(), pageable);
            }

            default: {
                return rUserRepository.findUsernameDistinct(pageable);
            }
        }
    }

    @Override
    public RUserPair findByName (String name) {
        List<RUser> list = rUserRepository.findByUsername(name);

        if (list.size() > 0) {
            List<RGroupReply> listGroup = rGroupReplyRepository.findGroupByUsername(name);
            List<RUser> listUser = rUserRepository.findUserAttributeByUsername(name);
            RUserPair pair = new RUserPairFactory(list, listUser, listGroup).create();
            return pair;
        }
        return null;
    }

    @Override
    public void save (RUserPair pair) {

        RUser rUser = new RUser();
        rUser.setId(pair.getId());
        rUser.setUsername(pair.getName());
        rUser.setUserval(pair.getPassword());
        rUser.setUserattr(RadiusAttribute.CLEARTEXT_PASSWORD);
        rUser.setUserop(RadiusOperation.ASSIGN);
        rUserRepository.save(rUser);

        List<String> list = rGroupReplyRepository.findGroupByUsernameFromMusrgrp(pair.getName());

        rUserRepository.saveAll(pair.getListUser(), pair.getName());

        if (pair.getListGroup() != null && pair.getListGroup().size() > 0) {

            if (list != null && list.size() > 0) {
                List<String> listXOR = new ArrayList<>();
                listXOR.addAll(list);
                list.removeAll(pair.getListGroup());
                pair.getListGroup().removeAll(listXOR);

                rUserRepository.deleteAllGroup(list, pair.getName());
                rUserRepository.addAllGroup(pair.getListGroup(), pair.getName());
            } else {
                rUserRepository.addAllGroup(pair.getListGroup(), pair.getName());
            }
        } else {
            if (list != null && list.size() > 0) {
                rUserRepository.deleteAllGroup(list, pair.getName());
            }
        }

    }

    @Override
    public void delete (String userName) {
        rUserRepository.delete(userName);
    }

    @Override
    public void delete (Long userId) {
        rUserRepository.delete(userId);
    }

    @Override
    public void deleteUserAttribute (Long userAttrId) {
        rUserRepository.deleteUserAttribute(userAttrId);
    }
}
