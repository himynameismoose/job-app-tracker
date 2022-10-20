package com.himynameismoose.jobapptracker.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128, nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<PositionDetails> details = new ArrayList<>();

    public Position() {
    }

    public Position(String name, Company company) {
        this.name = name;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<PositionDetails> getDetails() {
        return details;
    }

    public void setDetails(List<PositionDetails> details) {
        this.details = details;
    }

    public void addDetail(String status) {
        this.details.add(new PositionDetails(status, this));
    }

    public void setDetail(Integer id, String status) {
        this.details.add(new PositionDetails(id, status, this));
    }
}
