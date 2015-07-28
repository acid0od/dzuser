package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.Accounting;
import net.odtel.dzuser.api.model.CurrentUser;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.repository.AccountingRepository;
import net.odtel.dzuser.impl.repository.CurrentUserRepository;
import net.odtel.dzuser.impl.service.CurrentUserService;
import net.odtel.dzuser.impl.util.SearchPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("currentUserService")
public class CurrentUserServiceImpl implements CurrentUserService {
    @Autowired
    protected CurrentUserRepository currentUserRepository;
    @Autowired
    protected AccountingRepository accountingRepository;

    @Transactional(readOnly = true)
    public Page<CurrentUser> findAll (int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.ASC,
                "userName"));
        return currentUserRepository.findAll(pageable);
    }

    @Transactional(readOnly = false)
    public CurrentUser findByID (String iD) {
        return currentUserRepository.findOne(iD);
    }

    @Transactional(readOnly = false)
    public void remove (String userName) {
        currentUserRepository.delete(userName);
    }

    @Transactional(readOnly = true)
    public Page<CurrentUser> findAllWithOrder (int page, int size, String order,
                                               boolean typeSort) {

        Pageable pageable = new PageRequest(page, size, new Sort(
                typeSort ? Direction.ASC : Direction.DESC, order));

        return currentUserRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<CurrentUser> findByUserNameOrNasWithOrder (
            SearchRequestModel searchRequest, int page, int size, String order,
            boolean typeSort) {

        Pageable pageable = new PageRequest(page, size, new Sort(typeSort ? Direction.DESC : Direction.ASC, order));

        if (searchRequest == null) {
            return currentUserRepository.findAll(pageable);
        }

        if (searchRequest.getNasid() == null || searchRequest.getNasid().getId() == null) {
            searchRequest.setNasid(null);
        }

        SearchPattern searchPattern = new SearchPattern(searchRequest.getUserName());

        switch (searchPattern.getPar()) {
            case SearchPattern.FULL_NAME: {
                if (searchRequest.getNasid() == null) {
                    return currentUserRepository.findByUserName(searchPattern.getSearchPattern(), pageable);
                }
                return currentUserRepository.findByUserNameAndNas(searchPattern.getSearchPattern(), searchRequest.getNasid(), pageable);
            }
            case SearchPattern.PARTIAL_NAME: {
                if (searchRequest.getNasid() == null) {
                    return currentUserRepository.findByUserNameLike(searchPattern.getSearchPattern(), pageable);
                }
                return currentUserRepository.findByUserLikeNameAndNas(searchPattern.getSearchPattern(), searchRequest.getNasid(), pageable);
            }
            default: {
                if (searchRequest.getNasid() == null) {
                    return currentUserRepository.findAll(pageable);
                }
                return currentUserRepository.findByNas(searchRequest.getNasid(), pageable);
            }
        }
    }

    @Transactional(readOnly = true)
    public Page<Accounting> viewStatistics (String userName, int page, int size) {

        Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "timeStart"));
        return accountingRepository
                .findByUserNameOrderByTimeStart(userName, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Accounting> viewStatisticsWithSearchRequest (
            SearchRequestModel searchRequest, int pageNum, int defaultPageSize) {

        Pageable pageable = new PageRequest(pageNum, defaultPageSize, new Sort(Direction.DESC, "timeStart"));

        if (searchRequest == null) {
            return accountingRepository.findByUserNameLikeOrderByTimeStart("%", pageable);
        }

        if (searchRequest.getNasid() == null || searchRequest.getNasid().getId() == null) {
            searchRequest.setNasid(null);
        }

        SearchPattern searchPattern = new SearchPattern(searchRequest.getUserName());

        switch (searchPattern.getPar()) {
            case SearchPattern.FULL_NAME: {
                if (searchRequest.getNasid() == null) {
                    return accountingRepository.findByUserNameOrderByTimeStart(searchPattern.getSearchPattern(), pageable);
                }
                return accountingRepository.findByUserNameAndNasIdOrderByTimeStart(searchPattern.getSearchPattern(), searchRequest.getNasid(), pageable);
            }

            case SearchPattern.PARTIAL_NAME: {
                if (searchRequest.getNasid() == null) {
                    return accountingRepository.findByUserNameLikeOrderByTimeStart(searchPattern.getSearchPattern(), pageable);
                }
                return accountingRepository.findByUserNameLikeAndNasIdOrderByTimeStart(searchPattern.getSearchPattern(), searchRequest.getNasid(), pageable);
            }
            default: {
                if (searchRequest.getNasid() == null) {
                    return accountingRepository.findByUserNameLikeOrderByTimeStart("%", pageable);
                }
                return accountingRepository.findByNasIdOrderByTimeStart(searchRequest.getNasid(), pageable);
            }
        }
    }

}
