package com.detectionplatform.repository;

import com.detectionplatform.model.MitreTechnique;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MitreTechniqueRepository extends JpaRepository<MitreTechnique, Integer> {
    Optional<MitreTechnique> findByTechniqueId(String techniqueId);
}
