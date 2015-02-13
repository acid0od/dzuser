package net.odtel.usercheck.controller;

import net.odtel.usercheck.domain.RadiusGroup;
import net.odtel.usercheck.service.RadiusGroupService;
import net.odtel.usercheck.service.RadiusUserService;
import net.odtel.usercheck.web.utils.Page;
import net.odtel.usercheck.web.utils.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class RadiusGroupController {
    private Integer totalElementPerPage = 50;

    private SearchRequest searchRequest = new SearchRequest();

    @Autowired
    private RadiusUserService radiusUserService;

    @Autowired
    private RadiusGroupService radiusGroupService;


}
