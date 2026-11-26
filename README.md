Substantial Java codebase I own and maintain — Spring Boot, Spring Security, MySQL, Docker, JUnit, CI

# bug-tracker-java

**Substantial Java codebase I own and maintain — Spring Boot, Spring Security, MySQL, Docker, JUnit, CI**

A production-style bug-tracking API designed to make correctness, validation, edge cases, and maintainability reviewable. I use this codebase to author agentic coding tasks with task-specific rubrics and regression tests.

## Features

- Basic-authenticated REST API for bug CRUD
- Pagination with deterministic newest-first ordering
- Filtering by status and priority, including combined filters
- Jakarta validation for titles and descriptions
- Duplicate-title conflict detection and structured error responses
- Explicit invalid-page and empty-page behavior
- JPA persistence with MySQL in production and H2 for tests
- Six focused automated checks across service, controller, validation, and pagination paths

## Tech Stack

Java 17 · Spring Boot · Spring Security · Spring Data JPA · MySQL · Maven · JUnit 5 · Mockito · MockMvc · Docker · GitHub Actions

## How to Run

```bash
# Start a local MySQL database named bugtracker, then configure credentials if needed
mvn spring-boot:run
```

The API is available at `http://localhost:8080/api/bugs`. Requests use HTTP Basic authentication with the development user `reviewer`; override this configuration before production use.

## Tests

```bash
mvn test
```

Tests cover duplicate titles, invalid input, invalid page parameters, and the empty-page response path. CI runs the same Maven test suite on every push and pull request.

## RL Task Authoring

This repository is a maintained target for three agentic coding tasks: pagination correctness, validation and duplicate handling, and robust error handling. Each task is paired with a task-specific rubric and regression tests so an agent's implementation can be evaluated on correctness, edge cases, quality, and security rather than only compilation.
