package com.himynameismoose.jobapptracker.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a recruiter of the company
 */
@Entity
public class Recruiter {
    // Fields/Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 45, nullable = false, unique = true)
    private String name;
    // one recruiter, one company
    // I could not get OneToOne to work --temp solution
    @OneToMany
    @JoinColumn(name = "recruiter_id")
    private List<Company> companies = new ArrayList<>();

    // Getters & Setters
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

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
