package com.lesson.management.system.repositories;

import com.lesson.management.system.entities.GuardianEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianRepository extends JpaRepository<GuardianEntity, Long> {
}