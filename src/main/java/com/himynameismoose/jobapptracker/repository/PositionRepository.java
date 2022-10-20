package com.himynameismoose.jobapptracker.repository;

import com.himynameismoose.jobapptracker.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
