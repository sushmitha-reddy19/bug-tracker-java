package com.sushmitha.bugtracker.repository;

import com.sushmitha.bugtracker.model.Bug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BugRepository extends JpaRepository<Bug, Long> {
    Page<Bug> findByStatus(Bug.Status status, Pageable pageable);
    Page<Bug> findByPriority(Bug.Priority priority, Pageable pageable);
    Page<Bug> findByStatusAndPriority(Bug.Status status, Bug.Priority priority, Pageable pageable);
    Optional<Bug> findByTitleIgnoreCase(String title);
}
