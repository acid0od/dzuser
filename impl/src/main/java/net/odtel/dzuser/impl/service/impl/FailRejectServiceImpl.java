package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.FailReject;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.repository.FailRejectRepository;
import net.odtel.dzuser.impl.service.FailRejectService;
import net.odtel.dzuser.impl.util.SearchPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("failRejectService")
public class FailRejectServiceImpl implements FailRejectService {

    protected static final String DEFAULT_FAILREJECT_ORDER = "timeReject";

    @Autowired
    private FailRejectRepository failRejectRepository;

    @Transactional(readOnly = true)
    public Page<FailReject> viewStatistics (SearchRequestModel searchRequest, int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, DEFAULT_FAILREJECT_ORDER));

        if (searchRequest == null) {
            return failRejectRepository.findByUserNameLikeOrderByTimeReject("%", pageable);
        }

        if (searchRequest.getNasid() == null || searchRequest.getNasid().getId() == null) {
            searchRequest.setNasid(null);
        }

        SearchPattern searchPattern = new SearchPattern(searchRequest.getUserName());

        switch (searchPattern.getPar()) {
            case SearchPattern.FULL_NAME: {

                if (searchRequest.getNasid() == null) {
                    return failRejectRepository.findByUserNameOrderByTimeReject(searchPattern.getSearchPattern(), pageable);
                }

                return failRejectRepository.findByUserNameAndNasIpOrderByTimeReject(searchPattern.getSearchPattern(), searchRequest.getNasid().getIp(), pageable);
            }
            case SearchPattern.PARTIAL_NAME: {
                if (searchRequest.getNasid() == null) {
                    return failRejectRepository.findByUserNameLikeOrderByTimeReject(searchPattern.getSearchPattern(), pageable);
                }
                return failRejectRepository.findByUserNameLikeAndNasIpOrderByTimeReject(searchPattern.getSearchPattern(), searchRequest.getNasid().getIp(), pageable);
            }
            default: {
                if (searchRequest.getNasid() == null) {
                    return failRejectRepository.findByUserNameLikeOrderByTimeReject("%", pageable);
                }
                return failRejectRepository.findByUserNameLikeAndNasIpOrderByTimeReject("%", searchRequest.getNasid().getIp(), pageable);
            }

        }
    }

}
