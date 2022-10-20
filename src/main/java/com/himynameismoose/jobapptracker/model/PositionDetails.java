package com.himynameismoose.jobapptracker.model;

import javax.persistence.*;

@Entity
@Table(name = "position_details")
public class PositionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 45, nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    public PositionDetails(String status, Position position) {
        this.status = status;
        this.position = position;
    }

    public PositionDetails(Integer id, String status, Position position) {
        this.id = id;
        this.status = status;
        this.position = position;
    }

    public PositionDetails() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return status;
    }
}
