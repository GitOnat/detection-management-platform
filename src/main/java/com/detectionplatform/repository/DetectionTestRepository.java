package com.detectionplatform.repository;

import com.detectionplatform.model.DetectionTest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetectionTestRepository extends JpaRepository<DetectionTest, Integer> {
    List<DetectionTest> findByTechniqueId(Integer techniqueId);
    List<DetectionTest> findByTestType(DetectionTest.TestType testType);
}
