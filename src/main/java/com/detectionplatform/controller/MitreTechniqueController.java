package com.detectionplatform.controller;

import com.detectionplatform.model.MitreTechnique;
import com.detectionplatform.repository.MitreTechniqueRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/techniques")
@CrossOrigin(origins = "*")
public class MitreTechniqueController {

    private final MitreTechniqueRepository repository;

    public MitreTechniqueController(MitreTechniqueRepository repository) {
        this.repository = repository;
    }

    // GET /api/techniques
    @GetMapping
    public List<MitreTechnique> getAll() {
        return repository.findAll();
    }

    // GET /api/techniques/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MitreTechnique> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/techniques
    @PostMapping
    public MitreTechnique create(@RequestBody MitreTechnique technique) {
        return repository.save(technique);
    }

    // PUT /api/techniques/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MitreTechnique> update(@PathVariable Integer id,
                                                  @RequestBody MitreTechnique updated) {
        return repository.findById(id).map(existing -> {
            existing.setTechniqueId(updated.getTechniqueId());
            existing.setName(updated.getName());
            existing.setTactic(updated.getTactic());
            existing.setDescription(updated.getDescription());
            existing.setMitreUrl(updated.getMitreUrl());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/techniques/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
