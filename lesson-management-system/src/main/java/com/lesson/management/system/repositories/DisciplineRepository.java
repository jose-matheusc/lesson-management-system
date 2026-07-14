package com.lesson.management.system.repositories;

import com.lesson.management.system.entities.DisciplineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<DisciplineEntity, Long> {
}