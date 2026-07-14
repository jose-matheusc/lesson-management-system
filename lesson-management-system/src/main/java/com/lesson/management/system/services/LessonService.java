package com.lesson.management.system.services;

import com.lesson.management.system.entities.DisciplineEntity;
import com.lesson.management.system.entities.LessonEntity;
import com.lesson.management.system.entities.MentorEntity;
import com.lesson.management.system.entities.StudentEntity;
import com.lesson.management.system.repositories.DisciplineRepository;
import com.lesson.management.system.repositories.LessonRepository;
import com.lesson.management.system.repositories.MentorRepository;
import com.lesson.management.system.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonService {

    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;
    private final DisciplineRepository disciplineRepository;

    @Transactional(readOnly = true)
    public List<LessonEntity> findAll() {
        return lessonRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LessonEntity findById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Lesson not found with id " + id));
    }

    public LessonEntity save(LessonEntity lesson) {

        StudentEntity student = studentRepository.findById(
                        lesson.getStudent().getId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        MentorEntity mentor = mentorRepository.findById(
                        lesson.getMentor().getId())
                .orElseThrow(() ->
                        new RuntimeException("Mentor not found"));

        DisciplineEntity discipline = disciplineRepository.findById(
                        lesson.getDiscipline().getId())
                .orElseThrow(() ->
                        new RuntimeException("Discipline not found"));

        lesson.setStudent(student);
        lesson.setMentor(mentor);
        lesson.setDiscipline(discipline);

        return lessonRepository.save(lesson);

    }

    public LessonEntity update(Long id, LessonEntity lesson) {

        LessonEntity entity = findById(id);

        StudentEntity student = studentRepository.findById(
                        lesson.getStudent().getId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        MentorEntity mentor = mentorRepository.findById(
                        lesson.getMentor().getId())
                .orElseThrow(() ->
                        new RuntimeException("Mentor not found"));

        DisciplineEntity discipline = disciplineRepository.findById(
                        lesson.getDiscipline().getId())
                .orElseThrow(() ->
                        new RuntimeException("Discipline not found"));

        entity.setDate(lesson.getDate());
        entity.setStartTime(lesson.getStartTime());
        entity.setContent(lesson.getContent());
        entity.setClassStatus(lesson.getClassStatus());
        entity.setPaymentStatus(lesson.getPaymentStatus());
        entity.setClassType(lesson.getClassType());
        entity.setObservations(lesson.getObservations());
        entity.setPerformanceEvaluation(lesson.getPerformanceEvaluation());
        entity.setMaterialLink(lesson.getMaterialLink());

        entity.setStudent(student);
        entity.setMentor(mentor);
        entity.setDiscipline(discipline);

        return lessonRepository.save(entity);

    }

    public void delete(Long id) {

        LessonEntity entity = findById(id);

        lessonRepository.delete(entity);

    }

}