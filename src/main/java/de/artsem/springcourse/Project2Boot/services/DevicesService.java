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
@Transactional(readOnly = true)
public class DevicesService {

    private final DevicesRepository devicesRepository;
    private final OfficesRepository officesRepository;
    private final DeviceOfficeRepository deviceOfficeRepository;
    private final SystemTypesRepository systemTypesRepository;

    public DevicesService(DevicesRepository devicesRepository, OfficesRepository officesRepository,
                          DeviceOfficeRepository deviceOfficeRepository, SystemTypesRepository systemTypesRepository) {
        this.devicesRepository = devicesRepository;
        this.officesRepository = officesRepository;
        this.deviceOfficeRepository = deviceOfficeRepository;
        this.systemTypesRepository = systemTypesRepository;
    }

    public List<Device> findAll() {
        return devicesRepository.findAll();
    }

    @Transactional
    public void save(Device device) {
        devicesRepository.save(device);
    }

    public Device findById(int id) {
        return devicesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(int id) {
        devicesRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Device updatedDevice) {
        updatedDevice.setId(id);
        devicesRepository.save(updatedDevice);
    }

    @Transactional
    public void assign(int officeId, int deviceId, int systemTypeId, int quantity){
        if (quantity<=0){
            quantity=1;
        }
        DeviceOffice deviceOffice = new DeviceOffice();
        Optional<Device> optionalDevice = devicesRepository.findById(deviceId);
        Optional<Office> optionalOffice = officesRepository.findById(officeId);
        Optional<SystemType> optionalSystemType = systemTypesRepository.findById(systemTypeId);
        if (optionalDevice.isPresent() && optionalOffice.isPresent() && optionalSystemType.isPresent()){
            deviceOffice.setDevice(optionalDevice.get());
            deviceOffice.setOffice(optionalOffice.get());
            deviceOffice.setSystemType(optionalSystemType.get());
            deviceOffice.setQuantity(quantity);
            deviceOfficeRepository.save(deviceOffice);
        }
    }

    @Transactional
    public void delete (int deviceId , int officeId, int systemTypeId ){
        Optional<Office> optionalOffice = officesRepository.findById(officeId);
        Optional<Device> optionalDevice = devicesRepository.findById(deviceId);
        if (optionalDevice.isPresent() && optionalOffice.isPresent()){
            DeviceOffice deviceOffice = deviceOfficeRepository
                    .findByOfficeAndDevice(optionalOffice.get(), optionalDevice.get());
            deviceOfficeRepository.delete(deviceOffice);
        }
    }
}
