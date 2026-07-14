package com.lesson.management.system.controllers;

import com.lesson.management.system.entities.GuardianEntity;
import com.lesson.management.system.services.GuardianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guardians")
@RequiredArgsConstructor
public class GuardianController {

    private final GuardianService service;

    @GetMapping
    public ResponseEntity<List<GuardianEntity>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuardianEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<GuardianEntity> save(
            @Valid @RequestBody GuardianEntity guardian) {

        GuardianEntity saved = service.save(guardian);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuardianEntity> update(
            @PathVariable Long id,
            @Valid @RequestBody GuardianEntity guardian) {

        return ResponseEntity.ok(service.update(id, guardian));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}