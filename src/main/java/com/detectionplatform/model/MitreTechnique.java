package com.detectionplatform.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mitre_technique")
public class MitreTechnique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "technique_id", nullable = false, unique = true, length = 10)
    private String techniqueId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String tactic;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "mitre_url")
    private String mitreUrl;

    // Getters og setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTechniqueId() { return techniqueId; }
    public void setTechniqueId(String techniqueId) { this.techniqueId = techniqueId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTactic() { return tactic; }
    public void setTactic(String tactic) { this.tactic = tactic; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMitreUrl() { return mitreUrl; }
    public void setMitreUrl(String mitreUrl) { this.mitreUrl = mitreUrl; }
}
