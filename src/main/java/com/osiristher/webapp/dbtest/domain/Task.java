package com.osiristher.webapp.dbtest.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by DesiresDesigner on 3/23/16.
 */

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @OneToMany(mappedBy="task")
    private Set<Student_task> task_students;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    public Task() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Student_task> getTask_students() {
        return task_students;
    }

    public void setTask_students(Set<Student_task> task_students) {
        this.task_students = task_students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
