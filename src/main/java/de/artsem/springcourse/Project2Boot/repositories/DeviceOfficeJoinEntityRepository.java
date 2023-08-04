package de.artsem.springcourse.Project2Boot.repositories;

import de.artsem.springcourse.Project2Boot.models.DeviceOfficeJoinEntity;
import de.artsem.springcourse.Project2Boot.models.DeviceOfficeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceOfficeJoinEntityRepository extends JpaRepository<DeviceOfficeJoinEntity, DeviceOfficeKey> {
}
