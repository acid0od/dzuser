package net.odtel.usercheck.controller;

import java.util.List;

import net.odtel.usercheck.model.CurrentUser;
import net.odtel.usercheck.service.CurrentUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CurrentUserController {

	/*@Autowired
	private CurrentUserService currentUserService;
	*/
	@RequestMapping(value="/currentUsers", method = RequestMethod.GET)
	String getListController (Model model){
		
	/*	List<CurrentUser> currentUsers = currentUserService.findAll();

		model.addAttribute("currentUsers", currentUsers);
	*/
		return "currentUsers";
	}
}
