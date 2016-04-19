package com.osiristher.webapp.controller;

/**
 * Created by DesiresDesigner on 2/26/16.
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping("/login")
    public String index(Model model) {

        return "login";
    }

}