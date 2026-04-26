package com.lesson_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Discipline/Subject entity (e.g., Matemática, Português, História).
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "discipline")
public class DisciplineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "discipline")
    private Set<LessonEntity> lessons = new HashSet<>();
}

