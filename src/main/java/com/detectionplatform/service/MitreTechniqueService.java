package com.detectionplatform.service;

import com.detectionplatform.model.MitreTechnique;
import com.detectionplatform.repository.MitreTechniqueRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MitreTechniqueService {

    private final MitreTechniqueRepository repository;

    public MitreTechniqueService(MitreTechniqueRepository repository) {
        this.repository = repository;
    }

    public List<MitreTechnique> getAll() {
        return repository.findAll();
    }

    public Optional<MitreTechnique> getById(Integer id) {
        return repository.findById(id);
    }

    public MitreTechnique create(MitreTechnique technique) {
        return repository.save(technique);
    }

    public boolean delete(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
