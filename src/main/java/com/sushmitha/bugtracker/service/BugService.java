package com.sushmitha.bugtracker.service;

import com.sushmitha.bugtracker.dto.CreateBugRequest;
import com.sushmitha.bugtracker.exception.ApiExceptions;
import com.sushmitha.bugtracker.model.Bug;
import com.sushmitha.bugtracker.repository.BugRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BugService {
    private final BugRepository repository;
    public BugService(BugRepository repository) { this.repository = repository; }
    @Transactional public Bug create(CreateBugRequest request) {
        if (repository.findByTitleIgnoreCase(request.title().trim()).isPresent())
            throw new ApiExceptions.Conflict("A bug with this title already exists");
        return repository.save(new Bug(request.title().trim(), request.description().trim(), request.status(), request.priority()));
    }
    @Transactional(readOnly = true) public Page<Bug> list(Bug.Status status, Bug.Priority priority, Pageable pageable) {
        if (status != null && priority != null) return repository.findByStatusAndPriority(status, priority, pageable);
        if (status != null) return repository.findByStatus(status, pageable);
        if (priority != null) return repository.findByPriority(priority, pageable);
        return repository.findAll(pageable);
    }
    @Transactional(readOnly = true) public Bug get(Long id) { return repository.findById(id).orElseThrow(() -> new ApiExceptions.NotFound("Bug not found: " + id)); }
    @Transactional public Bug update(Long id, CreateBugRequest request) {
        Bug bug = get(id);
        repository.findByTitleIgnoreCase(request.title().trim()).filter(other -> !other.getId().equals(id)).ifPresent(other -> { throw new ApiExceptions.Conflict("A bug with this title already exists"); });
        bug.update(request.title().trim(), request.description().trim(), request.status(), request.priority());
        return bug;
    }
    public void delete(Long id) { if (!repository.existsById(id)) throw new ApiExceptions.NotFound("Bug not found: " + id); repository.deleteById(id); }
}
