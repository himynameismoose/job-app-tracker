package com.himynameismoose.jobapptracker.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a job application
 */
@Entity
public class Position {
    // fields/attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128, nullable = false, unique = true)
    private String name;
    // Many positions to a company
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    // One position with details
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List<PositionDetails> details = new ArrayList<>();

    // Constructors
    public Position() {
    }

    public Position(String name, Company company) {
        this.name = name;
        this.company = company;
    }

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

    /**
     * Helper method to add details to be used in the controller
     * @param status string from the new form view
     */
    public void addDetail(String status) {
        this.details.add(new PositionDetails(status, this));
    }

    /**
     * This method is used when position is updated by the form view
     * @param id the id of the product detail row
     * @param status the string pulled from the update product form view
     */
    public void setDetail(Integer id, String status) {
        this.details.add(new PositionDetails(id, status, this));
    }
}
