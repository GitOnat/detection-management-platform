package com.detectionplatform.controller;

import com.detectionplatform.model.DetectionTest;
import com.detectionplatform.service.DetectionTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = "*")
public class DetectionTestController {

    private final DetectionTestService service;

    public DetectionTestController(DetectionTestService service) {
        this.service = service;
    }

    // GET /api/tests
    @GetMapping
    public List<DetectionTest> getAll() {
        return service.getAll();
    }

    // GET /api/tests/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DetectionTest> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/tests
    @PostMapping
    public ResponseEntity<DetectionTest> create(@RequestBody Map<String, Object> body) {
        DetectionTest test = new DetectionTest();
        test.setName((String) body.get("name"));
        test.setDescription((String) body.get("description"));
        test.setYamlContent((String) body.get("yamlContent"));
        test.setPlatform((String) body.get("platform"));
        if (body.get("testType") != null) {
            test.setTestType(DetectionTest.TestType.valueOf((String) body.get("testType")));
        }
        Integer techniqueId = (Integer) body.get("techniqueId");
        return ResponseEntity.ok(service.create(test, techniqueId));
    }

    // DELETE /api/tests/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
