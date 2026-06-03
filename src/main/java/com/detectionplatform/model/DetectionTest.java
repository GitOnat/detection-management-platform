package com.detectionplatform.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "detection_test")
public class DetectionTest {

    public enum TestType { CUSTOM, ART }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "technique_id", nullable = false)
    private MitreTechnique technique;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_type", nullable = false)
    private TestType testType = TestType.CUSTOM;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "yaml_content", columnDefinition = "TEXT")
    private String yamlContent;

    @Column(length = 100)
    private String platform;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters og setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public MitreTechnique getTechnique() { return technique; }
    public void setTechnique(MitreTechnique technique) { this.technique = technique; }

    public TestType getTestType() { return testType; }
    public void setTestType(TestType testType) { this.testType = testType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getYamlContent() { return yamlContent; }
    public void setYamlContent(String yamlContent) { this.yamlContent = yamlContent; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
