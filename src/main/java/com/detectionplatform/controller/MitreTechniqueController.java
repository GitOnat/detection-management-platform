package com.detectionplatform.controller;

import com.detectionplatform.model.MitreTechnique;
import com.detectionplatform.service.MitreTechniqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/techniques")
@CrossOrigin(origins = "*")
public class MitreTechniqueController {

    private final MitreTechniqueService service;

    public MitreTechniqueController(MitreTechniqueService service) {
        this.service = service;
    }

    // GET /api/techniques
    @GetMapping
    public List<MitreTechnique> getAll() {
        return service.getAll();
    }

    // GET /api/techniques/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MitreTechnique> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/techniques
    @PostMapping
    public MitreTechnique create(@RequestBody MitreTechnique technique) {
        return service.create(technique);
    }

    // PUT /api/techniques/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MitreTechnique> update(@PathVariable Integer id,
                                                  @RequestBody MitreTechnique updated) {
        return service.update(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/techniques/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
