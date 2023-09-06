package de.artsem.springcourse.Project2Boot.controllers;


import de.artsem.springcourse.Project2Boot.models.SystemType;
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

}
