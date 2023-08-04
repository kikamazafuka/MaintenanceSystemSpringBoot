package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.DeviceOfficeJoinEntity;
import de.artsem.springcourse.Project2Boot.repositories.DeviceOfficeJoinEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceOfficeJoinEntityService {
    private final DeviceOfficeJoinEntityRepository deviceOfficeJoinEntityRepository;

    public DeviceOfficeJoinEntityService(DeviceOfficeJoinEntityRepository deviceOfficeJoinEntityRepository) {
        this.deviceOfficeJoinEntityRepository = deviceOfficeJoinEntityRepository;
    }

    public DeviceOfficeJoinEntity findById(){
    return new DeviceOfficeJoinEntity();
    }


}