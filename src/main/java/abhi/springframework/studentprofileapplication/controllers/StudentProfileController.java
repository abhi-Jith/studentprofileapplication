package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class StudentProfileController {
    public final StudentProfileService studentProfileService;

    @Autowired
    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @GetMapping
    @RequestMapping("/student/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("student",studentProfileService.findById(new Long(id)));
        return "student/show";
    }

    @GetMapping
    @RequestMapping("student/new")
    public String newStudentProfile(Model model){
        model.addAttribute("student", new StudentProfileCommand());

        return "student/studentform";
    }

    @GetMapping
    @RequestMapping("student/{id}/update")
    public String updateStudentProfile(@PathVariable String id, Model model){
        model.addAttribute("student", studentProfileService.findCommandById(Long.valueOf(id)));
        return  "student/studentform";
    }

    @PostMapping
    @RequestMapping("student")
    public String saveOrUpdate(@ModelAttribute StudentProfileCommand command){
        StudentProfileCommand savedCommand = studentProfileService.saveStudentProfileCommand(command);

        return "redirect:/student/"+ savedCommand.getId()+"/show" ;
    }

    @GetMapping
    @RequestMapping("student/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        studentProfileService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
