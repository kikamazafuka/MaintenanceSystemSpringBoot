package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.DeviceOffice;
import de.artsem.springcourse.Project2Boot.models.Employee;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.repositories.OfficesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OfficesService {
    private final OfficesRepository officesRepository;

    public OfficesService(OfficesRepository officesRepository) {
        this.officesRepository = officesRepository;
    }

    public List<Office> findAll() {
        return officesRepository.findAll();
    }

    @Transactional
    public void save(Office office) {
        officesRepository.save(office);
    }

    public Office findById(int id) {
        return officesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(int id, Office updatedOffice) {
        updatedOffice.setId(id);
        officesRepository.save(updatedOffice);
    }

    @Transactional
    public void delete(int id) {
        officesRepository.deleteById(id);
    }

    public List<Office> findByEmployeeId(Employee employee) {
        return employee.getOfficeList();
    }

    public List<DeviceOffice> getDevicesAndQuantitiesForOffice(int officeId) {
        Optional<Office> officeOptional = officesRepository.findById(officeId);
        if (officeOptional.isPresent()) {
            Office office  = officeOptional.get();
            return office.getDeviceOfficeList();
        }
        return new ArrayList<>();
    }

}
