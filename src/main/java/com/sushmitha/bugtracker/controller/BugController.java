package com.sushmitha.bugtracker.controller;

import com.sushmitha.bugtracker.dto.CreateBugRequest;
import com.sushmitha.bugtracker.model.Bug;
import com.sushmitha.bugtracker.service.BugService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bugs")
public class BugController {
    private final BugService service;
    public BugController(BugService service) { this.service = service; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Bug create(@Valid @RequestBody CreateBugRequest request) { return service.create(request); }
    @GetMapping public Page<Bug> list(@RequestParam(required = false) Bug.Status status, @RequestParam(required = false) Bug.Priority priority,
                                      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        if (page < 0 || size < 1 || size > 100) throw new IllegalArgumentException("page must be >= 0 and size must be between 1 and 100");
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return service.list(status, priority, pageable);
    }
    @GetMapping("/{id}") public Bug get(@PathVariable Long id) { return service.get(id); }
    @PutMapping("/{id}") public Bug update(@PathVariable Long id, @Valid @RequestBody CreateBugRequest request) { return service.update(id, request); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
