package com.lesson.management.system.services;

import com.lesson.management.system.entities.MentorEntity;
import com.lesson.management.system.repositories.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MentorService {

    private final MentorRepository repository;

    @Transactional(readOnly = true)
    public List<MentorEntity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public MentorEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Mentor not found with id: " + id));
    }

    public MentorEntity save(MentorEntity mentor) {
        return repository.save(mentor);
    }

    public MentorEntity update(Long id, MentorEntity mentor) {

        MentorEntity entity = findById(id);

        entity.setFullName(mentor.getFullName());
        entity.setEmail(mentor.getEmail());
        entity.setPhone(mentor.getPhone());

        return repository.save(entity);
    }

    public void delete(Long id) {

        MentorEntity entity = findById(id);

        repository.delete(entity);

    }

}