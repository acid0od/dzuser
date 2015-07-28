package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.model.RGroupReply;
import net.odtel.dzuser.api.model.RUser;
import net.odtel.dzuser.api.model.RUserPair;
import net.odtel.dzuser.api.model.RadiusAttribute;
import net.odtel.dzuser.api.model.RadiusOperation;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.service.NasService;
import net.odtel.dzuser.impl.service.RGroupReplyService;
import net.odtel.dzuser.impl.service.RUserService;
import net.odtel.dzuser.web.util.RUserPairValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@SessionAttributes("searchRequest")
@Scope("session")
public class RUserController {
    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 20;
    @Autowired
    protected NasService nasService;
    @Autowired
    protected RUserService rUserService;
    @Autowired
    protected RGroupReplyService rGroupReplyService;
    @Autowired
    private ResourceBundleMessageSource messageSource;
    private SearchRequestModel searchRequest = new SearchRequestModel();

    @ModelAttribute("rGroupReplyList")
    public Collection<RGroupReply> populateRGroupReply () {
        return rGroupReplyService.findAllDistinctByGroupreplyname();
    }

    @ModelAttribute("radiusAttribute")
    public Collection<RadiusAttribute> populateRadiusAttribute () {
        List<RadiusAttribute> stringList = new ArrayList<>();
        for (RadiusAttribute radiusAttribute : RadiusAttribute.values()) {
            stringList.add(radiusAttribute);
        }
        return stringList;
    }

    @ModelAttribute("radiusOperation")
    public Collection<RadiusOperation> populateRadiusOperation () {
        List<RadiusOperation> stringList = new ArrayList<>();
        for (RadiusOperation radiusOperation : RadiusOperation.values()) {
            stringList.add(radiusOperation);
        }
        return stringList;
    }

    @ModelAttribute("searchRequest")
    public SearchRequestModel get () {
        return searchRequest;
    }

    @ModelAttribute("nas")
    public Collection<Nas> populateNas () {
        return nasService.findActive();
    }

    @ModelAttribute("nasview")
    public Collection<Nas> populateNasView () {
        return nasService.findView();
    }

    @InitBinder("pair")
    protected void initBinder (WebDataBinder binder) {
        binder.setValidator(new RUserPairValidator());
    }

    @RequestMapping(value = "/ruserlist")
    public String rUserList (@RequestParam(value = "page", required = false) Integer page,
                             @ModelAttribute("searchRequest") SearchRequestModel searchRequest,
                             Model model) {
        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        Page<RUser> pageRUser = rUserService.findByUserName(searchRequest, pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("page", pageRUser);
        return "/ruserlist";
    }

    @RequestMapping(value = "/ruseredit/{name}", method = RequestMethod.GET)
    public String updateRUser (@ModelAttribute("searchRequest") SearchRequestModel searchRequest, @PathVariable String name, Model model) {
        RUserPair pair = rUserService.findByName(name);

        model.addAttribute("pair", pair);
        model.addAttribute("searchRequest", searchRequest);
        return "/ruseredit";
    }

    @RequestMapping(value = "/ruseredit/new", method = RequestMethod.GET)
    public String newRUser (@ModelAttribute("searchRequest") SearchRequestModel searchRequest, Model model) {
        RUserPair pair = new RUserPair();
        pair.setListGroup(new ArrayList<String>());

        model.addAttribute("pair", pair);
        model.addAttribute("searchRequest", searchRequest);
        return "/ruseredit";
    }


    @RequestMapping(value = "/ruseredit", method = RequestMethod.POST)
    public String updateRUserPost (@Valid @ModelAttribute("pair") RUserPair pair, BindingResult result, Model model) {
        int pageNum = DEFAULT_PAGE_NUM;

        if (result.hasErrors()) {
            return "/ruseredit";
        }
        rUserService.save(pair);

        Page<RUser> pageRUser = rUserService.findByUserName(searchRequest, pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("page", pageRUser);
        return "/ruserlist";
    }

    @RequestMapping(value = "/ruseredit", params = "addValues")
    public String updateUserAddValues (@ModelAttribute("pair") RUserPair pair, final BindingResult result) {
        RUser user = new RUser();
        user.setTableName("ruserreply");

        if (pair.getListUser() == null) {
            pair.setListUser(Collections.singletonList(user));
        } else {
            pair.getListUser().add(user);
        }
        return "/ruseredit";
    }

    @RequestMapping(value = "/ruseredit", params = "removeValue")
    public String updateUserRemoveValues (@ModelAttribute("pair") RUserPair pair, final BindingResult result, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeValue"));
        RUser userAttr = pair.getListUser().get(rowId.intValue());
        if (userAttr != null && userAttr.getId() != null && userAttr.getId() > 0L) {
            if (userAttr.getTableName().equals("ruserreply")) {
                rUserService.deleteUserAttribute(userAttr.getId());
            } else if (userAttr.getTableName().equals("ruser")) {
                rUserService.delete(userAttr.getId());
            }
        }
        pair.getListUser().remove(rowId.intValue());
        return "/ruseredit";
    }

}
