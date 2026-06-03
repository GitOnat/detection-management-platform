package com.detectionplatform.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_run")
public class TestRun {

    public enum Result { DETECTED, NOT_DETECTED, PARTIAL }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private DetectionTest test;

    @Column(name = "run_date", nullable = false)
    private LocalDate runDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Result result;

    @Column(name = "detection_tool", length = 100)
    private String detectionTool;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "screenshot_path")
    private String screenshotPath;

    @Column(name = "logged_at")
    private LocalDateTime loggedAt = LocalDateTime.now();

    // Getters og setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public DetectionTest getTest() { return test; }
    public void setTest(DetectionTest test) { this.test = test; }

    public LocalDate getRunDate() { return runDate; }
    public void setRunDate(LocalDate runDate) { this.runDate = runDate; }

    public Result getResult() { return result; }
    public void setResult(Result result) { this.result = result; }

    public String getDetectionTool() { return detectionTool; }
    public void setDetectionTool(String detectionTool) { this.detectionTool = detectionTool; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getScreenshotPath() { return screenshotPath; }
    public void setScreenshotPath(String screenshotPath) { this.screenshotPath = screenshotPath; }

    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
