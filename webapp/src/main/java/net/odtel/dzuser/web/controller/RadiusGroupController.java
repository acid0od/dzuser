package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.impl.service.RadiusGroupService;
import net.odtel.dzuser.impl.service.RadiusUserService;
import net.odtel.dzuser.web.util.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class RadiusGroupController {
    private Integer totalElementPerPage = 50;

    private SearchRequest searchRequest = new SearchRequest();

    @Autowired
    private RadiusUserService radiusUserService;

    @Autowired
    private RadiusGroupService radiusGroupService;


}
