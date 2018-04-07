package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.services.SkillSetService;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class SkillSetController {
    private final StudentProfileService studentProfileService;
    private final SkillSetService skillSetService;



    public SkillSetController(StudentProfileService studentProfileService, SkillSetService skillSetService) {
        this.studentProfileService = studentProfileService;
        this.skillSetService = skillSetService;
    }

    @GetMapping("/student/{studentId}/skillSets")
    public String listProjects(@PathVariable String studentId, Model model){
        log.debug("Getting Projects for studentProfile id:" +studentId);
        model.addAttribute("student",studentProfileService.findCommandById(Long.valueOf(studentId)));
        return "student/skillSet/skillList";
    }

    @GetMapping("student/{studentId}/skillSet/{id}/show")
    public String showListProjects(@PathVariable String studentId,
                                   @PathVariable String id, Model model){
        model.addAttribute("skill", skillSetService.findByStudentIdAndSkillSet(Long.valueOf(studentId), Long.valueOf(id)));
        return "student/skillSet/show";
    }
}
