package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.Device;
import de.artsem.springcourse.Project2Boot.models.DeviceOffice;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.models.SystemType;
import de.artsem.springcourse.Project2Boot.repositories.DeviceOfficeRepository;
import de.artsem.springcourse.Project2Boot.repositories.DevicesRepository;
import de.artsem.springcourse.Project2Boot.repositories.OfficesRepository;
import de.artsem.springcourse.Project2Boot.repositories.SystemTypesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceOfficeService {

    private final DeviceOfficeRepository deviceOfficeRepository;
    private final DevicesRepository devicesRepository;
    private final OfficesRepository officesRepository;
    private final SystemTypesRepository systemTypesRepository;

    public DeviceOfficeService(DeviceOfficeRepository deviceOfficeRepository, DevicesRepository devicesRepository, OfficesRepository officesRepository, SystemTypesRepository systemTypesRepository) {
        this.deviceOfficeRepository = deviceOfficeRepository;
        this.devicesRepository = devicesRepository;
        this.officesRepository = officesRepository;
        this.systemTypesRepository = systemTypesRepository;
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

    public List<DeviceOffice> getDevicesInOfficeAndSystemType(int officeId, int systemTypeId) {
        Optional<Office> optionalOffice = officesRepository.findById(officeId);
        Optional<SystemType> optionalSystemType = systemTypesRepository.findById(systemTypeId);
        List<DeviceOffice> deviceOfficeList = null;
        if (optionalOffice.isPresent() && optionalSystemType.isPresent()) {
            deviceOfficeList = deviceOfficeRepository
                    .findByOfficeAndSystemType(optionalOffice.get(), optionalSystemType.get());
        }
        return deviceOfficeList;
    }


}
