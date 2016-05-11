package com.osiristher.webapp.controller;

/**
 * Created by DesiresDesigner on 2/26/16.
 */

import com.osiristher.webapp.controller.wrapper.StudentCourseWrapper;
import com.osiristher.webapp.controller.wrapper.TrainerCourseWrapper;
import com.osiristher.webapp.dbtest.domain.*;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import com.osiristher.webapp.dbtest.service.StudentTaskRepo;
import com.osiristher.webapp.dbtest.service.TrainerRepo;
import com.osiristher.webapp.provider.UserInfo;
import com.osiristher.webapp.util.UserManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TrainerRepo trainerRepo;
    @Autowired
    StudentTaskRepo studentTaskRepo;

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
            mav.addObject("role", "Student");
            mav.addObject("progress", getStudentProgress(user.getId()));
            mav.addObject("user", studentRepo.findOne(user.getId()));
        } else if (UserManagerUtil.hasRole("ROLE_TRAINER")) {
            mav.addObject("user", trainerRepo.findOne(user.getId()));
            mav.addObject("role", "Trainer");
            mav.addObject("progress", getTrainerProgress(user.getId()));
            mav.setViewName("trainer_home");
        }
        return mav;
    }

    @RequestMapping("/test")
    public String test(Model model) {
        return "base_template";
    }

    private Map<Long, StudentCourseWrapper> getStudentProgress (long id) {
        Map<Long, StudentCourseWrapper> progress = new HashMap<>();
        Student s = studentRepo.findOne(id);
        for (Course c : s.getCourses()) {
            StudentCourseWrapper cw = new StudentCourseWrapper(c, studentRepo.findOne(id));
            progress.put(c.getId(), cw);
        }
        List<Student_task> student_tasks = studentTaskRepo.findByStudent(s);
        for (Student_task st : student_tasks) {
            progress.get(st.getTask().getCourse().getId()).addStudTask(st);
        }

        return progress;
    }

    private Map<Long, TrainerCourseWrapper> getTrainerProgress (long id) {
        Map<Long, TrainerCourseWrapper> progress = new HashMap<>();
        Trainer t = trainerRepo.findOne(id);
        for (Course c : t.getCourses()) {
            TrainerCourseWrapper cw = new TrainerCourseWrapper(c);

            for (Task task : c.getTasks()) {
                List<Student_task> student_tasks = studentTaskRepo.findByTask(task);
                for (Student_task st : student_tasks) {
                    //progress.get(st.getTask().getCourse().getId()).addStudTask(st);
                    cw.addStudentProgress(st);
                }
            }
            progress.put(c.getId(), cw);
        }

        return progress;
    }
}
