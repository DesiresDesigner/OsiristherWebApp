package com.osiristher.webapp.controller;

import com.osiristher.webapp.dbtest.domain.Course;
import com.osiristher.webapp.dbtest.domain.Task;
import com.osiristher.webapp.dbtest.service.CourseRepo;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import com.osiristher.webapp.dbtest.service.TaskRepo;
import com.osiristher.webapp.dbtest.service.TrainerRepo;
import com.osiristher.webapp.form.entity.Solution;
import com.osiristher.webapp.provider.UserInfo;
import com.osiristher.webapp.testingsystem.properties.Config;
import com.osiristher.webapp.testingsystem.tester.OsiristherNative;
import com.osiristher.webapp.testingsystem.tester.exceptions.ConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by DesiresDesigner on 4/21/16.
 */
@Controller
public class CourseController {
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TrainerRepo trainerRepo;

    //ToDo обрабатывать ConfigException
    @RequestMapping("/task/{task_id}")
    public @ResponseBody
    ModelAndView taskPage(@PathVariable(value="task_id") long taskID) throws ConfigException {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskRepo.findOne(taskID);
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("username",user.getScreenName());
        mav.addObject("course_id", task.getCourse().getId());
        mav.addObject("task", task);
        if (user.getRole().equals("ROLE_STUDENT")) {
            //mav.setViewName("student_task");
            mav.addObject("solution", new Solution());
            mav.addObject("role", "Student");
            mav.addObject("user", studentRepo.findOne(user.getId()));
        } else if (user.getRole().equals("ROLE_TRAINER")) {
            //mav.setViewName("trainer_task");
            mav.addObject("role", "Trainer");
            mav.addObject("link_to_fitnesse", Config.getProp("linkToFitNesseWeb"));
            mav.addObject("user", trainerRepo.findOne(user.getId()));
        }
        mav.setViewName("task");
        return mav;
    }

    @RequestMapping(value="/newtask/{course_id}", method= RequestMethod.GET)
    public @ResponseBody
    ModelAndView newTaskForm(@PathVariable(value="course_id") long courseID) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole().equals("ROLE_STUDENT")) {
            return new ModelAndView("redirect:/home");
        }
        ModelAndView mav = new ModelAndView("admin");
        if (user.getRole().equals("ROLE_TRAINER")) {
            mav.setViewName("trainer_newtask");
            mav.addObject("user", trainerRepo.findOne(user.getId()));
            mav.addObject("role", "Педагог");
        }
        mav.addObject("username",user.getScreenName());
        mav.addObject("course_id", courseID);
        return mav;
    }

    @RequestMapping(value="/newtask/{course_id}", method= RequestMethod.POST)
    public @ResponseBody
    ModelAndView saveNewTask(@PathVariable(value="course_id") long courseID, @ModelAttribute Task task) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole().equals("ROLE_STUDENT")) {
            return new ModelAndView("redirect:/home");
        }
        task.setCourse(courseRepo.findOne(courseID));
        taskRepo.save(task);
        return new ModelAndView("redirect:/task/" + task.getId());
    }

    @RequestMapping(value="/changetask/{task_id}", method= RequestMethod.GET)
    public @ResponseBody
    ModelAndView changeTaskForm(@PathVariable(value="task_id") long taskID) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole().equals("ROLE_STUDENT")) {
            return new ModelAndView("redirect:/home");
        }
        ModelAndView mav = new ModelAndView("admin");
        if (user.getRole().equals("ROLE_TRAINER")) {
            mav.setViewName("trainer_changetask");
            mav.addObject("user", trainerRepo.findOne(user.getId()));
            mav.addObject("role", "Педагог");
        }
        mav.addObject("username",user.getScreenName());
        mav.addObject("task", taskRepo.findOne(taskID));
        return mav;
    }

    @RequestMapping(value="/changetask/{task_id}", method= RequestMethod.POST)
    public @ResponseBody
    ModelAndView changeNewTask(@PathVariable(value="task_id") long taskID, @ModelAttribute Task task) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole().equals("ROLE_STUDENT")) {
            return new ModelAndView("redirect:/home");
        }
        Task oldTask = taskRepo.findOne(taskID);
        oldTask.setName(task.getName());
        oldTask.setDescription(task.getDescription());
        taskRepo.save(oldTask);
        return new ModelAndView("redirect:/task/" + oldTask.getId());
    }

    @RequestMapping(value="/test/{task_id}", method= RequestMethod.POST)
    public @ResponseBody
    ModelAndView testTask(@PathVariable(value="task_id") long taskID, @ModelAttribute Solution solution) {
        UserInfo user = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getRole().equals("ROLE_STUDENT")) {
            return new ModelAndView("redirect:/home");
        }

        //ToDo Real Data on TaskID
        OsiristherNative.getInstance().testSource(user.getId(), taskID, solution.getSource(), solution.getLanguage());
        //OsiristherNative.getInstance().testSource(user.getId(), 1, solution.getSource(), solution.getLanguage());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("expecting_result");
        mav.addObject("user", studentRepo.findOne(user.getId()));
        mav.addObject("role", "Студент");
        return mav;
    }
}
