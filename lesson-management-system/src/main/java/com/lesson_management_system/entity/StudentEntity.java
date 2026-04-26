package com.lesson_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    @PositiveOrZero
    private Double baseHourlyRate;

    @NotBlank
    private String schoolYear;

    @NotBlank
    private String goal;

    /**
     * Business rule: Every student must be linked to at least one guardian.
     * Managed through StudentGuardianMapping (which stores relationship type/kinship).
     */
    @Valid
    @NotEmpty(message = "Um aluno deve estar vinculado a pelo menos um responsável")
    @OneToMany(mappedBy = "student")
    private Set<StudentGuardianMapping> guardianMappings = new HashSet<>();
}
