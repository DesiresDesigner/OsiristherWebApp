package com.osiristher.webapp.dbtest.domain;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by DesiresDesigner on 3/23/16.
 */

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "lrid", unique = true, nullable = false)
    private Long lrid;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @OneToMany(mappedBy="student")
    private Set<Student_task> student_tasks;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="student_course",
            joinColumns={@JoinColumn(name="student_id")},
            inverseJoinColumns={@JoinColumn(name="course_id")})
    private Set<Course> courses = new HashSet<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student_task> getStudent_tasks() {
        return student_tasks;
    }

    public void setStudent_tasks(Set<Student_task> student_tasks) {
        this.student_tasks = student_tasks;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Long getLrid() {
        return lrid;
    }

    public void setLr_id(Long lrid) {
        this.lrid = lrid;
    }
}
