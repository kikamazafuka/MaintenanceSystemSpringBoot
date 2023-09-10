package de.artsem.springcourse.Project2Boot.controllers;


import de.artsem.springcourse.Project2Boot.models.Device;
import de.artsem.springcourse.Project2Boot.models.DeviceOffice;
import de.artsem.springcourse.Project2Boot.models.Office;
import de.artsem.springcourse.Project2Boot.models.SystemType;
import de.artsem.springcourse.Project2Boot.services.DeviceOfficeService;
import de.artsem.springcourse.Project2Boot.services.OfficesService;
import de.artsem.springcourse.Project2Boot.services.SystemTypesService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system_types")
public class SystemTypesController {


    private final SystemTypesService systemTypesService;
    private final OfficesService officesService;
    private final DeviceOfficeService deviceOfficeService;

    public SystemTypesController(SystemTypesService systemTypesService, OfficesService officesService, DeviceOfficeService deviceOfficeService) {
        this.systemTypesService = systemTypesService;
        this.officesService = officesService;
        this.deviceOfficeService = deviceOfficeService;
    }


    @GetMapping
    private String index (Model model){
        List<SystemType> systemTypeList = systemTypesService.findAll();
        model.addAttribute("systemTypes", systemTypeList);
        return "systemTypes/index";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable ("id") int id, Model model){
        model.addAttribute("systemType",systemTypesService.findById(id));
        return "systemTypes/show";
    }

    @GetMapping("/new")
    public String newSystemType (@ModelAttribute ("systemType") SystemType systemType){
        return "systemTypes/new";
    }

    @PostMapping
    public String create(@ModelAttribute ("systemType") @Valid SystemType systemType,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "systemTypes/new";
        }
        systemTypesService.save(systemType);
        return "redirect:/system_types";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        systemTypesService.delete(id);
        return "redirect:/system_types";
    }

    @GetMapping("/{id_system_type}/{id_office}/edit")
    public String editDevicesInSystemType(@PathVariable ("id_system_type") int systemTypeId,
                                          @PathVariable ("id_office") int officeId, Model model){
        SystemType systemType = systemTypesService.findById(systemTypeId);
        Office office = officesService.findById(officeId);

        List<DeviceOffice> deviceOffice = deviceOfficeService.getDevicesInOfficeAndSystemType(officeId, systemTypeId);
        model.addAttribute("systemType", systemType);
        model.addAttribute("office", office);
        model.addAttribute("deviceOffice", deviceOffice);

        return "offices/system_type_edit";
    }

}
