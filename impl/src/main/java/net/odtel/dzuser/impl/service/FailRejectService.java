package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.FailReject;
import net.odtel.dzuser.api.web.SearchRequestModel;
import org.springframework.data.domain.Page;

public interface FailRejectService {

    Page<FailReject> viewStatistics (SearchRequestModel searchRequest, int page, int size);
}
