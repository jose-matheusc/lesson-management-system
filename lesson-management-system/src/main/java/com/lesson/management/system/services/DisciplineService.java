package com.lesson.management.system.services;

import com.lesson.management.system.repositories.DisciplineRepository;
import com.lesson.management.system.entities.DisciplineEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DisciplineService {

    private final DisciplineRepository repository;

    @Transactional(readOnly = true)
    public List<DisciplineEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public DisciplineEntity findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Discipline not found with id: " + id));

    }

    public DisciplineEntity save(DisciplineEntity discipline) {
        return repository.save(discipline);
    }

    public DisciplineEntity update(Long id, DisciplineEntity discipline) {

        DisciplineEntity entity = findById(id);

        entity.setName(discipline.getName());

        return repository.save(entity);

    }

    public void delete(Long id) {

        DisciplineEntity entity = findById(id);

        repository.delete(entity);

    }

}