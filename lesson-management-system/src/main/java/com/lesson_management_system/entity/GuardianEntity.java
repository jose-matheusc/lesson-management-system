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
 * Guardian/Responsible entity for students.
 * A responsible person can take care of multiple students.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "guardian")
public class GuardianEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    /**
     * Multiple phone numbers (can be stored comma-separated or as a simple field).
     */
    private String phones;

    /**
     * Multiple email addresses (can be stored comma-separated or as a simple field).
     */
    private String emails;

    /**
     * Address of the guardian.
     */
    private String address;

    @OneToMany(mappedBy = "guardian")
    private Set<StudentGuardianMapping> studentMappings = new HashSet<>();
}

