package com.lesson.management.system.repositories;

import com.lesson.management.system.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
}