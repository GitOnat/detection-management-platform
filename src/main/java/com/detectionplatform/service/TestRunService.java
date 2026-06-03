package com.detectionplatform.service;

import com.detectionplatform.model.DetectionTest;
import com.detectionplatform.model.MitreTechnique;
import com.detectionplatform.model.TestRun;
import com.detectionplatform.repository.DetectionTestRepository;
import com.detectionplatform.repository.TestRunRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TestRunService {

    private final TestRunRepository runRepository;
    private final DetectionTestRepository testRepository;

    public TestRunService(TestRunRepository runRepository,
                          DetectionTestRepository testRepository) {
        this.runRepository = runRepository;
        this.testRepository = testRepository;
    }

    public List<TestRun> getAll() {
        return runRepository.findAll();
    }

    public List<TestRun> getByTestId(Integer testId) {
        return runRepository.findByTestId(testId);
    }

    public TestRun create(TestRun run, Integer testId) {
        DetectionTest test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test ikke fundet: " + testId));
        run.setTest(test);
        return runRepository.save(run);
    }

    public void delete(Integer id) {
        runRepository.deleteById(id);
    }

    // Coverage: returnerer en oversigt over alle teknikker og deres bedste resultat
    public List<Map<String, Object>> getCoverage() {
        List<TestRun> allRuns = runRepository.findAll();
        Map<Integer, Map<String, Object>> coverageMap = new LinkedHashMap<>();

        for (TestRun run : allRuns) {
            MitreTechnique technique = run.getTest().getTechnique();
            int techId = technique.getId();

            if (!coverageMap.containsKey(techId)) {
                Map<String, Object> entry = new LinkedHashMap<>();
                entry.put("techniqueId", technique.getTechniqueId());
                entry.put("name", technique.getName());
                entry.put("tactic", technique.getTactic());
                entry.put("bestResult", run.getResult().name());
                entry.put("runCount", 1);
                coverageMap.put(techId, entry);
            } else {
                Map<String, Object> entry = coverageMap.get(techId);
                entry.put("runCount", (int) entry.get("runCount") + 1);
                // DETECTED > PARTIAL > NOT_DETECTED
                String current = (String) entry.get("bestResult");
                String incoming = run.getResult().name();
                if (isBetter(incoming, current)) {
                    entry.put("bestResult", incoming);
                }
            }
        }

        return new ArrayList<>(coverageMap.values());
    }

    private boolean isBetter(String incoming, String current) {
        List<String> order = List.of("NOT_DETECTED", "PARTIAL", "DETECTED");
        return order.indexOf(incoming) > order.indexOf(current);
    }
}
