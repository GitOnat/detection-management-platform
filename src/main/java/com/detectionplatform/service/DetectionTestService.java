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

    public void delete(Integer id) {
        testRepository.deleteById(id);
    }
}
