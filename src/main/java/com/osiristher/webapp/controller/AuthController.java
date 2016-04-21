package com.osiristher.webapp.controller;

/**
 * Created by DesiresDesigner on 2/26/16.
 */
import com.osiristher.webapp.dbtest.domain.Course;
import com.osiristher.webapp.dbtest.domain.Student;
import com.osiristher.webapp.dbtest.domain.Task;
import com.osiristher.webapp.dbtest.service.CourseRepo;
import com.osiristher.webapp.dbtest.service.StudentRepo;
import com.osiristher.webapp.dbtest.service.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    TaskRepo taskRepo;

    @RequestMapping("/login")
    public String index(Model model) {

        /*Course course = new Course();
        course.setName("Алгоритмы и структуры данных");
        courseRepo.save(course);

        Task quickSort = new Task();
        quickSort.setName("Сортировка Quick Sort");
        quickSort.setDescription("Напишите прорамму, которая принимает " +
                "на вход длину массива, массив, и выводит его в " +
                "отсортированном виде. Сортировку осуществить методом Quick Sort");
        quickSort.setCourse(course);

        Task mergeSort = new Task();
        mergeSort.setName("Сортировка Merge Sort");
        mergeSort.setDescription("Напишите прорамму, которая принимает " +
                "на вход длину массива, массив, и выводит его в " +
                "отсортированном виде. Сортировку осуществить методом Merge Sort");
        mergeSort.setCourse(course);

        taskRepo.save(quickSort);
        taskRepo.save(mergeSort);

        Course course1 = new Course();
        course1.setName("Системное программное обеспечение");
        courseRepo.save(course1);

        Task quickSort1 = new Task();
        quickSort1.setName("Работа с вводом и выводом");
        quickSort1.setDescription("Напишите прорамму, которая принимает " +
                "на вход строковую переменную и выводит строку" +
                "\"На вход поступила переменная: \" с добавлением этой переменной в конец.");
        quickSort1.setCourse(course1);

        Task mergeSort1 = new Task();
        mergeSort1.setName("Сумматор");
        mergeSort1.setDescription("Напишите прорамму, которая принимает " +
                "на вход длину интового массива и выводит сумму его значений.");
        mergeSort1.setCourse(course1);

        taskRepo.save(quickSort1);
        taskRepo.save(mergeSort1);*/

        /*Student student = studentRepo.findOne(1l);
        Set courseSet = new HashSet<Course>();
        courseSet.add(courseRepo.findOne(2l));
        courseSet.add(courseRepo.findOne(5l));
        student.setCourses(courseSet);
        studentRepo.save(student);

        Course course1 = courseRepo.findOne(2l);
        Course course2 = courseRepo.findOne(5l);
        Set studSet = new HashSet<Student>();
        studSet.add(student);
        course1.setStudents(studSet);
        course2.setStudents(studSet);
        courseRepo.save(course1);
        courseRepo.save(course2);*/

        return "login";
    }

}