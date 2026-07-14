package com.lesson.management.system.controllers;

import com.lesson.management.system.entities.LessonEntity;
import com.lesson.management.system.services.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService service;

    @GetMapping
    public ResponseEntity<List<LessonEntity>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonEntity> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));

    }

    @PostMapping
    public ResponseEntity<LessonEntity> save(
            @Valid @RequestBody LessonEntity lesson) {

        LessonEntity saved = service.save(lesson);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonEntity> update(
            @PathVariable Long id,
            @Valid @RequestBody LessonEntity lesson) {

        return ResponseEntity.ok(service.update(id, lesson));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

}