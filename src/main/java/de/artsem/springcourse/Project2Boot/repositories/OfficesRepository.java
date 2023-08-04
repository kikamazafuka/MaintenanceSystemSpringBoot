package de.artsem.springcourse.Project2Boot.repositories;

import de.artsem.springcourse.Project2Boot.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficesRepository extends JpaRepository<Office, Integer> {

}
