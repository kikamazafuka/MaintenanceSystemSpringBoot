package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.repositories.DeviceOfficeRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceOfficeService {

    private final DeviceOfficeRepository deviceOfficeRepository;

    public DeviceOfficeService(DeviceOfficeRepository deviceOfficeRepository) {
        this.deviceOfficeRepository = deviceOfficeRepository;
    }


}
