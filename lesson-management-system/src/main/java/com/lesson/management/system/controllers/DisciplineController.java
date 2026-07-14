package com.lesson.management.system.controllers;

import com.lesson.management.system.entities.DisciplineEntity;
import com.lesson.management.system.services.DisciplineService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService service;

    public DisciplineController(DisciplineService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DisciplineEntity>> findAll() {

        return ResponseEntity.ok(service.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineEntity> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));

    }

    @PostMapping
    public ResponseEntity<DisciplineEntity> save(
            @Valid @RequestBody DisciplineEntity discipline) {

        DisciplineEntity saved = service.save(discipline);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineEntity> update(
            @PathVariable Long id,
            @Valid @RequestBody DisciplineEntity discipline) {

        return ResponseEntity.ok(service.update(id, discipline));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

}