package com.lesson_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Lesson/Class (Aula) entity.
 * Represents a recorded teaching event.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lesson")
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date of the lesson.
     */
    @NotNull
    private LocalDate date;

    /**
     * Start time of the lesson.
     */
    @NotNull
    private LocalTime startTime;

    /**
     * Content/topics covered in the lesson.
     */
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Lesson status: AGENDADA (scheduled), REALIZADA (completed), CANCELADA (cancelled).
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private LessonStatus classStatus;

    /**
     * Weekly payment status: PENDENTE (pending), PAGO (paid).
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDENTE;

    /**
     * Lesson type: ONLINE or PRESENCIAL (in-person).
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private LessonType classType;

    /**
     * Optional observations about the lesson.
     */
    @Column(columnDefinition = "TEXT")
    private String observations;

    /**
     * Optional performance evaluation (numeric or text).
     */
    @PositiveOrZero
    private Double performanceEvaluation;

    /**
     * Optional link to teaching material (URL).
     */
    @Pattern(regexp = "^(https?://.*)?$", message = "Material link must be a valid URL")
    private String materialLink;

    /**
     * Mentor/Teacher responsible for the lesson.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_lesson_mentor"))
    private MentorEntity mentor;

    /**
     * Student attending the lesson.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false, foreignKey = @ForeignKey(name = "fk_lesson_student"))
    private StudentEntity student;

    /**
     * Discipline/Subject of the lesson.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false, foreignKey = @ForeignKey(name = "fk_lesson_discipline"))
    private DisciplineEntity discipline;

    public enum LessonStatus {
        AGENDADA,  // Scheduled
        REALIZADA, // Completed
        CANCELADA  // Cancelled
    }

    public enum PaymentStatus {
        PENDENTE, // Pending
        PAGO      // Paid
    }

    public enum LessonType {
        ONLINE,
        PRESENCIAL
    }
}

