package com.osiristher.webapp.controller;

/**
 * Created by DesiresDesigner on 2/26/16.
 */

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import com.osiristher.webapp.provider.UserInfo;
import com.osiristher.webapp.util.UserManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    StudentRepo studentRepo;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("attr", "i'm attribute");
        return "home";
    }

    @RequestMapping("/home") //routeUserAfterLogin home
    public String home(Model model) {
        UserInfo user = (UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username",user.getScreenName());
        if (user.getRole().equals("ROLE_STUDENT")) {
            //Student student = studentRepo.findOne(user.getId());
            return "student_home";
        }
        if (UserManagerUtil.hasRole("ROLE_TRAINER"))
            return "trainer";
        return "admin";
    }

    @RequestMapping("/test")
    public String test(Model model) {

        return "base_template";
    }

}
