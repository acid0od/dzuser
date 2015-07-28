package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.model.RGroupReply;
import net.odtel.dzuser.api.model.RGroupReplyPair;
import net.odtel.dzuser.api.model.RadiusAttribute;
import net.odtel.dzuser.api.model.RadiusOperation;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.service.NasService;
import net.odtel.dzuser.impl.service.RGroupReplyService;
import net.odtel.dzuser.web.util.RGroupReplyPairValidator;
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
public class RGroupReplyController {
    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 20;
    @Autowired
    protected NasService nasService;
    @Autowired
    protected RGroupReplyService rGroupReplyService;
    @Autowired
    private ResourceBundleMessageSource messageSource;
    private SearchRequestModel searchRequest = new SearchRequestModel();

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
        binder.setValidator(new RGroupReplyPairValidator());
    }

    @RequestMapping(value = "/rgroupreplylist")
    public String rGroupReplyList (@RequestParam(value = "page", required = false) Integer page, Model model) {
        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        Page<RGroupReply> pageRGroupReplies = rGroupReplyService.findAll(pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("page", pageRGroupReplies);
        return "/rgroupreplylist";
    }

    @RequestMapping(value = "/rgroupreplyedit/{name}", method = RequestMethod.GET)
    public String updateRGroupReply (@PathVariable String name, Model model) {

        RGroupReplyPair pair = rGroupReplyService.findByName(name);

        model.addAttribute("pair", pair);
        return "/rgroupreplyedit";
    }

    @RequestMapping(value = "/rgroupreplyedit/new", method = RequestMethod.GET)
    public String newRGroupReply (Model model) {

        RGroupReplyPair rGroupReplyPair = new RGroupReplyPair();
        rGroupReplyPair.setList(Collections.singletonList(new RGroupReply()));
        model.addAttribute("pair", rGroupReplyPair);
        return "/rgroupreplyedit";
    }

    @RequestMapping(value = "/rgroupreplydelete/{name}", method = RequestMethod.GET)
    public String deleteRGroupReply (@PathVariable String name, Model model) {
        if (name != null) {
            rGroupReplyService.delete(name);
        }
        return "redirect:/rgroupreplylist.html";
    }

    @RequestMapping(value = "/rgroupreplyedit", method = RequestMethod.POST)
    public String updateRGroupReplyPost (@Valid @ModelAttribute("pair") RGroupReplyPair pair, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "/rgroupreplyedit";
        } else {
            rGroupReplyService.save(pair);
        }
        return "redirect:/rgroupreplylist.html";
    }

    @RequestMapping(value = "/rgroupreplyedit", params = "addValues")
    public String updateRadiusUserAddValues (@ModelAttribute("pair") RGroupReplyPair pair, final BindingResult result) {
        if (pair.getList() == null) {
            pair.setList(Collections.singletonList(new RGroupReply()));
        } else {
            pair.getList().add(new RGroupReply());
        }
        return "/rgroupreplyedit";
    }

    @RequestMapping(value = "/rgroupreplyedit", params = "removeValue")
    public String updateRadiusUserRemoveValues (@ModelAttribute("pair") RGroupReplyPair pair, final BindingResult result, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeValue"));
        RGroupReply reply = pair.getList().get(rowId.intValue());
        if (reply != null && reply.getId() != null && reply.getId() > 0L) {
            rGroupReplyService.delete(reply.getId());
        }
        pair.getList().remove(rowId.intValue());
        return "/rgroupreplyedit";
    }

}
