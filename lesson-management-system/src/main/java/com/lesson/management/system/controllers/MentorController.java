package com.lesson.management.system.controllers;

import com.lesson.management.system.entities.MentorEntity;
import com.lesson.management.system.services.MentorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorController {

    private final MentorService service;

    @GetMapping
    public ResponseEntity<List<MentorEntity>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MentorEntity> save(
            @Valid @RequestBody MentorEntity mentor) {

        MentorEntity saved = service.save(mentor);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentorEntity> update(
            @PathVariable Long id,
            @Valid @RequestBody MentorEntity mentor) {

        return ResponseEntity.ok(service.update(id, mentor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}