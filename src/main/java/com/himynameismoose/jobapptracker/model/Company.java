package com.himynameismoose.jobapptracker.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

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

    @OneToMany(mappedBy = "company")
    private Collection<Application> application;

    public Collection<Application> getApplication() {
        return application;
    }

    public void setApplication(Collection<Application> application) {
        this.application = application;
    }
}
