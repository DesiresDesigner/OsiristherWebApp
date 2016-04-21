package com.osiristher.webapp.controller;

import com.osiristher.webapp.dbtest.domain.Task;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import com.osiristher.webapp.dbtest.service.TaskRepo;
import com.osiristher.webapp.dbtest.service.TrainerRepo;
import com.osiristher.webapp.provider.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by DesiresDesigner on 4/21/16.
 */
@Controller
public class NavigateController {
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TrainerRepo trainerRepo;

    //ToDo меню сделать общим, и наполнять только контентный блок?
    @RequestMapping("/task/{task_id}")
    public @ResponseBody
    ModelAndView taskPage(@PathVariable(value="task_id") long taskID) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskRepo.findOne(taskID);
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("username",user.getScreenName());
        mav.addObject("course_id", task.getCourse().getId());
        mav.addObject("task", task);
        if (user.getRole().equals("ROLE_STUDENT")) {
            mav.setViewName("student_task");
            mav.addObject("student", studentRepo.findOne(user.getId()));
        } else if (user.getRole().equals("ROLE_TRAINER")) {
            mav.setViewName("trainer_task");
            mav.addObject("student", trainerRepo.findOne(user.getId()));
        }

        return mav;
    }
}
