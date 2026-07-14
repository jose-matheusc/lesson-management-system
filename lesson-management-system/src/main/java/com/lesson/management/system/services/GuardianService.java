package com.lesson.management.system.services;

import com.lesson.management.system.entities.GuardianEntity;
import com.lesson.management.system.repositories.GuardianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GuardianService {

    private final GuardianRepository repository;

    @Transactional(readOnly = true)
    public List<GuardianEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public GuardianEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Guardian not found with id: " + id));
    }

    public GuardianEntity save(GuardianEntity guardian) {
        return repository.save(guardian);
    }

    public GuardianEntity update(Long id, GuardianEntity guardian) {

        GuardianEntity entity = findById(id);

        entity.setFullName(guardian.getFullName());
        entity.setPhones(guardian.getPhones());
        entity.setEmails(guardian.getEmails());
        entity.setAddress(guardian.getAddress());

        return repository.save(entity);
    }

    public void delete(Long id) {

        GuardianEntity entity = findById(id);

        repository.delete(entity);

    }

}