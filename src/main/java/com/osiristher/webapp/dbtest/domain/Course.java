package com.osiristher.webapp.dbtest.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by DesiresDesigner on 3/23/16.
 */

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @OneToMany(mappedBy="course")
    private Set<Task> tasks;

    @ManyToMany
    private Set<Student> students = new HashSet<>();

    @ManyToMany
    private Set<Trainer> trainers = new HashSet<>();

    public Course() {
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }
}
