package com.sushmitha.bugtracker.dto;

import com.sushmitha.bugtracker.model.Bug;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateBugRequest(
    @NotBlank @Size(max = 160) String title,
    @NotBlank @Size(max = 2000) String description,
    @NotNull Bug.Status status,
    @NotNull Bug.Priority priority
) {}
