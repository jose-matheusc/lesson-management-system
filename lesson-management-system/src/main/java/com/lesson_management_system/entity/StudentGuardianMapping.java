package com.lesson_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Association entity between Student and Guardian that records the kinship/relationship type.
 * Example: Grau de Parentesco = "Mãe", "Pai", "Avó", "Tio", etc.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student_guardian")
public class StudentGuardianMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, foreignKey = @ForeignKey(name = "fk_student_guardian_student"))
    private StudentEntity student;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "guardian_id", nullable = false, foreignKey = @ForeignKey(name = "fk_student_guardian_guardian"))
    private GuardianEntity guardian;

    /**
     * Degree of kinship/relationship type (e.g., "Mãe", "Pai", "Avó", "Tio", "Responsável Legal").
     */
    @NotBlank
    private String relationshipType;
}

