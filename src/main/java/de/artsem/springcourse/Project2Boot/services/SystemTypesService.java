package de.artsem.springcourse.Project2Boot.services;

import de.artsem.springcourse.Project2Boot.models.SystemType;
import de.artsem.springcourse.Project2Boot.repositories.SystemTypesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemTypesService {

    private final SystemTypesRepository systemTypesRepository;

    public SystemTypesService(SystemTypesRepository systemTypesRepository) {
        this.systemTypesRepository = systemTypesRepository;
    }

    public List<SystemType> findAll(){
        return systemTypesRepository.findAll();
    }

    @Transactional
    public void save (SystemType systemType){
        systemTypesRepository.save(systemType);
    }

    @Transactional
    public void delete(int id) {
        systemTypesRepository.deleteById(id);
    }
}
