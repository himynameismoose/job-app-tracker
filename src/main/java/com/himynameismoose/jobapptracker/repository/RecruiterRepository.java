package com.himynameismoose.jobapptracker.repository;

import com.himynameismoose.jobapptracker.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {
}
