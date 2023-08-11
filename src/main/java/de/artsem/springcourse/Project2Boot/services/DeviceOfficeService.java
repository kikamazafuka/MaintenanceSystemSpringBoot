package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.Device;
import de.artsem.springcourse.Project2Boot.models.DeviceOffice;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.repositories.DeviceOfficeRepository;
import de.artsem.springcourse.Project2Boot.repositories.DevicesRepository;
import de.artsem.springcourse.Project2Boot.repositories.OfficesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DeviceOfficeService {

    private final DeviceOfficeRepository deviceOfficeRepository;
    private final DevicesRepository devicesRepository;
    private final OfficesRepository officesRepository;

    public DeviceOfficeService(DeviceOfficeRepository deviceOfficeRepository, DevicesRepository devicesRepository, OfficesRepository officesRepository) {
        this.deviceOfficeRepository = deviceOfficeRepository;
        this.devicesRepository = devicesRepository;
        this.officesRepository = officesRepository;
    }

    @Transactional
    public void setQuantity(int officeId, int deviceId, int quantity){
        Optional<Device> optionalDevice = devicesRepository.findById(deviceId);
        Optional<Office> optionalOffice = officesRepository.findById(officeId);
        if (optionalDevice.isPresent() && optionalOffice.isPresent()){
            DeviceOffice deviceOffice = deviceOfficeRepository
                    .findByOfficeAndDevice(optionalOffice.get(), optionalDevice.get());
            deviceOffice.setQuantity(quantity);
            deviceOfficeRepository.save(deviceOffice);
        }

    }


}
