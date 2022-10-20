package com.himynameismoose.jobapptracker.repository;

import com.himynameismoose.jobapptracker.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
