package com.osiristher.webapp.controller;

/**
 * Created by DesiresDesigner on 2/26/16.
 */

import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import com.osiristher.webapp.dbtest.service.TrainerRepo;
import com.osiristher.webapp.provider.UserInfo;
import com.osiristher.webapp.util.UserManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TrainerRepo trainerRepo;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("attr", "i'm attribute");
        return "home";
    }

    @RequestMapping("/home")
    public ModelAndView home() {
        UserInfo user = (UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("username",user.getScreenName());
        if (user.getRole().equals("ROLE_STUDENT")) {
            mav.setViewName("student_home");
            mav.addObject("student", studentRepo.findOne(user.getId()));
        }
        if (UserManagerUtil.hasRole("ROLE_TRAINER")) {
            mav.addObject("trainer", trainerRepo.findOne(user.getId()));
            mav.setViewName("trainer_home");
        }
        return mav;
    }

    @RequestMapping("/test")
    public String test(Model model) {

        return "base_template";
    }

}
