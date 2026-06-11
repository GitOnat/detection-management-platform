package com.detectionplatform.repository;

import com.detectionplatform.model.DetectionTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetectionTestRepository extends JpaRepository<DetectionTest, Integer> {
}
