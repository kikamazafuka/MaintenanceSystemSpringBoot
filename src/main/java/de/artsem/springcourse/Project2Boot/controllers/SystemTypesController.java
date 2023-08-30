package de.artsem.springcourse.Project2Boot.controllers;


import de.artsem.springcourse.Project2Boot.models.SystemType;
import de.artsem.springcourse.Project2Boot.services.SystemTypesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/system_types")
public class SystemTypesController {


    private final SystemTypesService systemTypesService;

    public SystemTypesController(SystemTypesService systemTypesService) {
        this.systemTypesService = systemTypesService;
    }


    @GetMapping
    private String index (Model model){
        List<SystemType> systemTypeList = systemTypesService.findAll();
        model.addAttribute("systemTypes", systemTypeList);
        return "systemTypes/index";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable ("id") int id){
        return "systemTypes/index";
    }

    @GetMapping("/new")
    public String newSystemType (@ModelAttribute ("systemType") SystemType systemType){
        return "systemTypes/new";
    }

}