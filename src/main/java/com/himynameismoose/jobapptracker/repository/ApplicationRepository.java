package com.himynameismoose.jobapptracker.repository;

import com.himynameismoose.jobapptracker.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
