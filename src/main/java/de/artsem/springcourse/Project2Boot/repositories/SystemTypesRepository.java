package de.artsem.springcourse.Project2Boot.repositories;

import de.artsem.springcourse.Project2Boot.models.SystemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemTypesRepository extends JpaRepository<SystemType, Integer> {
}
