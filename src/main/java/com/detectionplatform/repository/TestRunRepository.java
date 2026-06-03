package com.detectionplatform.repository;

import com.detectionplatform.model.TestRun;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TestRunRepository extends JpaRepository<TestRun, Integer> {
    List<TestRun> findByTestId(Integer testId);
    List<TestRun> findByResult(TestRun.Result result);
}
