package com.lesson.management.system.services;

import com.lesson.management.system.entities.StudentEntity;
import com.lesson.management.system.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final StudentRepository repository;

    @Transactional(readOnly = true)
    public List<StudentEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public StudentEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + id));
    }

    public StudentEntity save(StudentEntity student) {
        return repository.save(student);
    }

    public StudentEntity update(Long id, StudentEntity student) {

        StudentEntity entity = findById(id);

        entity.setFullName(student.getFullName());
        entity.setBirthDate(student.getBirthDate());
        entity.setBaseHourlyRate(student.getBaseHourlyRate());
        entity.setSchoolYear(student.getSchoolYear());
        entity.setGoal(student.getGoal());

        entity.setGuardianMappings(student.getGuardianMappings());

        return repository.save(entity);
    }

    public void delete(Long id) {

        StudentEntity entity = findById(id);

        repository.delete(entity);

    }

}