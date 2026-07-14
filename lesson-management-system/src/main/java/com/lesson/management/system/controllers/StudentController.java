package com.lesson.management.system.controllers;

import com.lesson.management.system.entities.StudentEntity;
import com.lesson.management.system.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StudentEntity>> findAll() {

        return ResponseEntity.ok(service.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));

    }

    @PostMapping
    public ResponseEntity<StudentEntity> save(
            @Valid @RequestBody StudentEntity student) {

        StudentEntity saved = service.save(student);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentEntity student) {

        return ResponseEntity.ok(service.update(id, student));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }

}