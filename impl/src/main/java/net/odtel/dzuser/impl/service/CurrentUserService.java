package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.Accounting;
import net.odtel.dzuser.api.model.CurrentUser;
import net.odtel.dzuser.api.web.SearchRequestModel;
import org.springframework.data.domain.Page;

public interface CurrentUserService {

    Page<CurrentUser> findAll (int page, int size);

    Page<CurrentUser> findAllWithOrder (int page, int size, String order, boolean typeSort);

    Page<CurrentUser> findByUserNameOrNasWithOrder (SearchRequestModel searchRequest, int page, int size, String order, boolean typeSort);

    void remove (String userName);

    Page<Accounting> viewStatistics (String userName, int pageNum, int defaultPageSize);

    Page<Accounting> viewStatisticsWithSearchRequest (SearchRequestModel searchRequest, int pageNum, int defaultPageSize);
}
