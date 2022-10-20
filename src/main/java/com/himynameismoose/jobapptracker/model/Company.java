package com.himynameismoose.jobapptracker.model;

import javax.persistence.*;

/**
 * This class represents the position's company at
 */
@Entity
public class Company {
    // Fields/Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 45, nullable = false, unique = true)
    private String name;

    // Logically, this does not make sense
    // I could not get @OneToOne to work
    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    // Constructors
    public Company() {
    }

    public Company(Integer id) {
        this.id = id;
    }

    public Company(String name) {
        this.name = name;
    }

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    @Override
    public String toString() {
        return name;
    }
}
