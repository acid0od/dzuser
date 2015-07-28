package net.odtel.dzuser.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    // Login form
    @RequestMapping("/login.html")
    public String login () {
        return "/login";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError (Model model) {
        model.addAttribute("loginError", true);
        return "/login";
    }

    @RequestMapping("/index.html")
    public String root () {
        return "redirect:/currentusers.html";
    }

    @RequestMapping("/")
    public String rootHtml () {
        return "redirect:/currentusers.html";
    }

    // Error page
    @RequestMapping("/error.html")
    public String error (HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = null;
        if (throwable != null) {
            errorMessage = throwable.getMessage();
        }
        model.addAttribute("errorMessage", errorMessage);
        return "/error";
    }

    @RequestMapping("/403-error.html")
    public String accessError (HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String errorMessage = null;
        if (throwable != null) {
            errorMessage = throwable.getMessage();
        }
        model.addAttribute("errorMessage", errorMessage);
        return "/403-error";
    }
}
