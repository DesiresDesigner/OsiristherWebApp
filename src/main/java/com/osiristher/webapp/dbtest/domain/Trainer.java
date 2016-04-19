package com.osiristher.webapp.dbtest.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by DesiresDesigner on 3/23/16.
 */

@Entity
@Table(name = "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "lrid", unique = true, nullable = false)
    private Long lrid;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="trainer_course",
            joinColumns={@JoinColumn(name="trainer_id")},
            inverseJoinColumns={@JoinColumn(name="course_id")})
    private Set<Course> courses = new HashSet<>();

    public Trainer() {
    }

    public Long getLrid() {
        return lrid;
    }

    public void setLrid(Long lrid) {
        this.lrid = lrid;
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
