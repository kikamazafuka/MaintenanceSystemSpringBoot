package de.artsem.springcourse.Project2Boot.repositories;

import de.artsem.springcourse.Project2Boot.models.EmployeeOfficeJoinEntity;
import de.artsem.springcourse.Project2Boot.models.EmployeeOfficeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeOfficeJoinEntityRepository extends JpaRepository<EmployeeOfficeJoinEntity, EmployeeOfficeKey> {
}
