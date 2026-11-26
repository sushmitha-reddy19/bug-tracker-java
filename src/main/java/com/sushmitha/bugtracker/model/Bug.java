package com.sushmitha.bugtracker.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "bugs", uniqueConstraints = @UniqueConstraint(name = "uk_bug_title", columnNames = "title"))
public class Bug {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 160) private String title;
    @Column(nullable = false, length = 2000) private String description;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private Status status;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private Priority priority;
    @Column(nullable = false, updatable = false) private Instant createdAt;

    public enum Status { OPEN, IN_PROGRESS, RESOLVED, CLOSED }
    public enum Priority { LOW, MEDIUM, HIGH, CRITICAL }

    protected Bug() {}
    public Bug(String title, String description, Status status, Priority priority) {
        this.title = title; this.description = description; this.status = status; this.priority = priority;
    }
    @PrePersist void onCreate() { if (createdAt == null) createdAt = Instant.now(); }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Status getStatus() { return status; }
    public Priority getPriority() { return priority; }
    public Instant getCreatedAt() { return createdAt; }
    public void update(String title, String description, Status status, Priority priority) {
        this.title = title; this.description = description; this.status = status; this.priority = priority;
    }
}
