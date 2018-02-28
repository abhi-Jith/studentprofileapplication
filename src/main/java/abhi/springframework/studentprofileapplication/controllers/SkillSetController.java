package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class SkillSetController {
    private final StudentProfileService studentProfileService;

    public SkillSetController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @GetMapping
    @RequestMapping("/student/{studentId}/skillSets")
    public String listProjects(@PathVariable String studentId, Model model){
        log.debug("Getting Projects for studentProfile id:" +studentId);
        model.addAttribute("student",studentProfileService.findCommandById(Long.valueOf(studentId)));
        return "student/skillSet/skillList";
    }
}
