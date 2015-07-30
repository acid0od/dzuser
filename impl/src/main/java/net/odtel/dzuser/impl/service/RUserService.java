package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.RUser;
import net.odtel.dzuser.api.model.RUserPair;
import net.odtel.dzuser.api.web.SearchRequestModel;
import org.springframework.data.domain.Page;

public interface RUserService {

    Page<RUser> findAll (int page, int size);

    Page<RUser> findByUserName (SearchRequestModel searchRequest, int pageNum, int defaultPageSize);

    RUserPair findByName (String name);

    void save (RUserPair pair);

    void delete (String userName);

    void delete (Long userId);

    void deleteUserAttribute (Long userAttrId);

}
