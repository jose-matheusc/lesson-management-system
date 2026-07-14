package com.lesson.management.system.repositories;

import com.lesson.management.system.entities.MentorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<MentorEntity, Long> {
}