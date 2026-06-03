package com.detectionplatform.controller;

import com.detectionplatform.model.TestRun;
import com.detectionplatform.service.TestRunService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/runs")
@CrossOrigin(origins = "*")
public class TestRunController {

    private final TestRunService service;

    public TestRunController(TestRunService service) {
        this.service = service;
    }

    // GET /api/runs
    @GetMapping
    public List<TestRun> getAll() {
        return service.getAll();
    }

    // GET /api/runs/by-test/{testId}
    @GetMapping("/by-test/{testId}")
    public List<TestRun> getByTest(@PathVariable Integer testId) {
        return service.getByTestId(testId);
    }

    // POST /api/runs
    @PostMapping
    public ResponseEntity<TestRun> create(@RequestBody Map<String, Object> body) {
        TestRun run = new TestRun();
        run.setRunDate(LocalDate.parse((String) body.get("runDate")));
        run.setResult(TestRun.Result.valueOf((String) body.get("result")));
        run.setDetectionTool((String) body.get("detectionTool"));
        run.setNotes((String) body.get("notes"));
        run.setScreenshotPath((String) body.get("screenshotPath"));
        Integer testId = (Integer) body.get("testId");
        return ResponseEntity.ok(service.create(run, testId));
    }

    // DELETE /api/runs/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/runs/coverage — MITRE ATT&CK coverage oversigt
    @GetMapping("/coverage")
    public List<Map<String, Object>> getCoverage() {
        return service.getCoverage();
    }
}
