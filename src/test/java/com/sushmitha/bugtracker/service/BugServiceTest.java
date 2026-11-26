package com.sushmitha.bugtracker.service;

import com.sushmitha.bugtracker.dto.CreateBugRequest;
import com.sushmitha.bugtracker.exception.ApiExceptions;
import com.sushmitha.bugtracker.model.Bug;
import com.sushmitha.bugtracker.repository.BugRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BugServiceTest {
    @Test void rejectsDuplicateTitles() {
        BugRepository repository = Mockito.mock(BugRepository.class);
        when(repository.findByTitleIgnoreCase("Duplicate")).thenReturn(java.util.Optional.of(new Bug("Duplicate", "old", Bug.Status.OPEN, Bug.Priority.LOW)));
        BugService service = new BugService(repository);
        assertThrows(ApiExceptions.Conflict.class, () -> service.create(new CreateBugRequest("Duplicate", "new", Bug.Status.OPEN, Bug.Priority.LOW)));
    }
}
