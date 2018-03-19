package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.EducationalQualificationCommand;
import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.services.EducationalQualificationService;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class EducationalQualificationController {
    private final StudentProfileService studentProfileService;
    private final EducationalQualificationService educationalQualificationService;

    @Autowired
    public EducationalQualificationController(StudentProfileService studentProfileService, EducationalQualificationService educationalQualificationService) {
        this.studentProfileService = studentProfileService;
        this.educationalQualificationService = educationalQualificationService;
    }

    @GetMapping("/student/{studentId}/educationalQualifications")
    public String listEducationalQualifications(@PathVariable String studentId, Model model) {
        log.debug("Getting Educational Qualification for studentProfile id:" + studentId);
        model.addAttribute("student", studentProfileService.findCommandById(Long.valueOf(studentId)));
        return "student/educationalQualification/qualificationList";
    }

    @GetMapping("student/{studentId}/educationalQualification/new")
    public String newEducationalQualification(@PathVariable String studentId, Model model) {
        StudentProfileCommand studentProfileCommand = studentProfileService.findCommandById(Long.valueOf(studentId));

        EducationalQualificationCommand educationalQualificationCommand = new EducationalQualificationCommand();
        educationalQualificationCommand.setStudentProfileId(Long.valueOf(studentId));
        model.addAttribute("educational", educationalQualificationCommand);
        return "student/educationalQualification/educationalform";

    }

    @GetMapping("student/{studentId}/educationalQualification/{id}/show")
    public String showListEducationQualifications(@PathVariable String studentId,
                                                  @PathVariable String id, Model model) {
        model.addAttribute("educational", educationalQualificationService.findByStudentIdAndEducationalId(Long.valueOf(studentId), Long.valueOf(id)));
        return "student/educationalQualification/show";
    }

    @GetMapping("student/{studentId}/educationalQualification/{id}/update")
    public String updateEducationalQualification(@PathVariable String studentId,
                                                 @PathVariable String id, Model model) {
        model.addAttribute("educational", educationalQualificationService.findByStudentIdAndEducationalId(Long.valueOf(studentId), Long.valueOf(id)));
        return "student/educationalQualification/educationalform";
    }

    @PostMapping("student/{studentId}/educationalQualification")
    public String saveOrUpdate(@ModelAttribute EducationalQualificationCommand command,@PathVariable String studentId) {
        EducationalQualificationCommand savedCommand = educationalQualificationService.saveEducationalQualificationCommand(command,Long.valueOf(studentId));

        log.debug("saved student id:" + savedCommand.getStudentProfileId());
        log.debug("saved educational Qualification id:" + savedCommand.getId());

        return "redirect:/student/" + savedCommand.getStudentProfileId() + "/educationalQualification/" + savedCommand.getId() + "/show";
    }

    @GetMapping("student/{studentId}/educationalQualification/{id}/delete")
    public String deleteEducationalQualification(@PathVariable String studentId,
                                   @PathVariable String id){

        log.debug("deleting educational id:" + id);
        educationalQualificationService.deleteById(Long.valueOf(studentId), Long.valueOf(id));

        return "redirect:/student/" + studentId + "/educationalQualifications";
    }

}
