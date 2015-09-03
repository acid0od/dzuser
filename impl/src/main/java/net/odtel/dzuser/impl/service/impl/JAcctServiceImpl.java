package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.JAcct;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.repository.JAcctRepository;
import net.odtel.dzuser.impl.service.JAcctService;
import net.odtel.dzuser.impl.util.SearchPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("jAcctService")
public class JAcctServiceImpl implements JAcctService {

    @Autowired
    private JAcctRepository jAcctRepository;

    @Override
    public Page<JAcct> viewTopLastMonth (SearchRequestModel searchRequest, int pageNum, int defaultPageSize) {
        Pageable pageable = new PageRequest(pageNum, defaultPageSize, new Sort(Sort.Direction.DESC, "out"));

        if (searchRequest == null) {
            return jAcctRepository.viewTopLastMonth(pageable);
        }

        SearchPattern searchPattern = new SearchPattern(searchRequest.getUserName());

        switch (searchPattern.getPar()) {
            case SearchPattern.FULL_NAME: {
                if (searchRequest.getNasid() == null || searchRequest.getNasid().getId() == null) {
                    return jAcctRepository.viewTopLastMonth(searchPattern.getSearchPattern(), pageable);
                } else {
                    return jAcctRepository.viewTopLastMonth(searchPattern.getSearchPattern(), searchRequest.getNasid().getId(), pageable);
                }
            }

            case SearchPattern.PARTIAL_NAME: {
                if (searchRequest.getNasid() == null || searchRequest.getNasid().getId() == null) {
                    return jAcctRepository.viewTopLastMonthLike(searchPattern.getSearchPattern(), pageable);
                } else {
                    return jAcctRepository.viewTopLastMonthLike(searchPattern.getSearchPattern(), searchRequest.getNasid().getId(), pageable);
                }
            }

            default: {
                if (searchRequest.getNasid() == null || searchRequest.getNasid().getId() == null) {
                    return jAcctRepository.viewTopLastMonth(pageable);
                } else {
                    return jAcctRepository.viewTopLastMonthLike("%", searchRequest.getNasid().getId(), pageable);
                }
            }
        }

    }
}
