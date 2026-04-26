package com.lesson_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Mentor/Teacher entity for lessons.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mentor")
public class MentorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    @Email
    private String email;

    private String phone;

    @OneToMany(mappedBy = "mentor")
    private Set<LessonEntity> lessons = new HashSet<>();
}

