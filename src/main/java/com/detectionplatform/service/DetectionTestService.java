package com.detectionplatform.service;

import com.detectionplatform.model.DetectionTest;
import com.detectionplatform.model.MitreTechnique;
import com.detectionplatform.repository.DetectionTestRepository;
import com.detectionplatform.repository.MitreTechniqueRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DetectionTestService {

    private final DetectionTestRepository testRepository;
    private final MitreTechniqueRepository techniqueRepository;

    public DetectionTestService(DetectionTestRepository testRepository,
                                MitreTechniqueRepository techniqueRepository) {
        this.testRepository = testRepository;
        this.techniqueRepository = techniqueRepository;
    }

    public List<DetectionTest> getAll() {
        return testRepository.findAll();
    }

    public Optional<DetectionTest> getById(Integer id) {
        return testRepository.findById(id);
    }

    public DetectionTest create(DetectionTest test, Integer techniqueId) {
        MitreTechnique technique = techniqueRepository.findById(techniqueId)
                .orElseThrow(() -> new RuntimeException("Teknik ikke fundet: " + techniqueId));
        test.setTechnique(technique);
        return testRepository.save(test);
    }

    public DetectionTest update(Integer id, DetectionTest updated, Integer techniqueId) {
        DetectionTest existing = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test ikke fundet: " + id));
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setYamlContent(updated.getYamlContent());
        existing.setTestType(updated.getTestType());
        existing.setPlatform(updated.getPlatform());
        if (techniqueId != null) {
            MitreTechnique technique = techniqueRepository.findById(techniqueId)
                    .orElseThrow(() -> new RuntimeException("Teknik ikke fundet: " + techniqueId));
            existing.setTechnique(technique);
        }
        return testRepository.save(existing);
    }

    public void delete(Integer id) {
        testRepository.deleteById(id);
    }
}
