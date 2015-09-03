package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.JAcct;
import net.odtel.dzuser.api.web.SearchRequestModel;
import org.springframework.data.domain.Page;

public interface JAcctService {

    Page<JAcct> viewTopLastMonth (SearchRequestModel searchRequest, int pageNum, int defaultPageSize);

}
