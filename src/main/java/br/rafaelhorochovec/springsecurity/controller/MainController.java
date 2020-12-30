package br.rafaelhorochovec.springsecurity.controller;
 
import java.security.Principal;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.rafaelhorochovec.springsecurity.util.WebUtils;
 
@Controller
public class MainController {
 
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Olá!");
        model.addAttribute("message", "Bem vindo a página inicial.");
        return "index";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
        User usuarioLogado = (User) ((Authentication) principal).getPrincipal();
        String usuarioInfo = WebUtils.toString(usuarioLogado);
        model.addAttribute("usuarioInfo", usuarioInfo);
        return "admin";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "login";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessful";
    }
 
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        // After user login successfully.
        String usuario = principal.getName();
 
        System.out.println("UserName: " + usuario);
 
        User usuarioLogado = (User) ((Authentication) principal).getPrincipal();
        String usuarioInfo = WebUtils.toString(usuarioLogado);
        model.addAttribute("usuarioInfo", usuarioInfo);
        return "user";
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User usuarioLogado = (User) ((Authentication) principal).getPrincipal();
            String usuarioInfo = WebUtils.toString(usuarioLogado);
            model.addAttribute("usuarioInfo", usuarioInfo);
            String message = "Opa!!!  " + principal.getName() //
                    + "<br> Você não deveria estar aqui.";
            model.addAttribute("message", message);
 
        }
        return "403";
    }
}