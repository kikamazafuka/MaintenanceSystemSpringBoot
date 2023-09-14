package de.artsem.springcourse.Project2Boot.repositories;

import de.artsem.springcourse.Project2Boot.models.Device;
import de.artsem.springcourse.Project2Boot.models.DeviceOffice;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.models.SystemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceOfficeRepository extends JpaRepository<DeviceOffice, Integer> {

    DeviceOffice findByOfficeAndDevice(Office office, Device device);

    List<DeviceOffice> findByOfficeAndSystemType(Office office, SystemType systemType);
}
