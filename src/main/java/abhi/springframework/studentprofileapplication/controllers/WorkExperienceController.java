package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.commands.UnitOfDurationCommand;
import abhi.springframework.studentprofileapplication.commands.WorkExperienceCommand;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import abhi.springframework.studentprofileapplication.services.UnitOfDurationService;
import abhi.springframework.studentprofileapplication.services.WorkExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class WorkExperienceController {
    private final StudentProfileService studentProfileService;
    private final WorkExperienceService workExperienceService;
    private final UnitOfDurationService unitOfDurationService;

    public WorkExperienceController(StudentProfileService studentProfileService,
                                    WorkExperienceService workExperienceService, UnitOfDurationService unitOfDurationService) {
        this.studentProfileService = studentProfileService;
        this.workExperienceService = workExperienceService;
        this.unitOfDurationService = unitOfDurationService;
    }

    @GetMapping("/student/{studentId}/workExperiences")
    public String listWorkExperience(@PathVariable String studentId, Model model){

        log.debug("Getting Work Experience for studentProfile id:" +studentId);
        model.addAttribute("student",studentProfileService.findCommandById(Long.valueOf(studentId)));

        return "student/workExperience/workList";
    }

    @GetMapping("student/{studentId}/workExperiences/new")
    public String newWorkExperience(@PathVariable String studentId, Model model) {
        StudentProfileCommand studentProfileCommand = studentProfileService.findCommandById(Long.valueOf(studentId));

        WorkExperienceCommand workExperienceCommand = new WorkExperienceCommand();
        workExperienceCommand.setStudentProfileId(Long.valueOf(studentId));
        model.addAttribute("work", workExperienceCommand);
        workExperienceCommand.setUnitOfDuration(new UnitOfDurationCommand());

        model.addAttribute("uodList",  unitOfDurationService.listAllUod());
        return "student/workExperience/workform";

    }
    @GetMapping("student/{studentId}/workExperiences/{id}/show")
    public String showListWorkExperience(@PathVariable String studentId,
                                   @PathVariable String id, Model model){
        model.addAttribute("workExperience", workExperienceService.findByStudentIdAndWorkExperienceId(Long.valueOf(studentId), Long.valueOf(id)));
        return "student/workExperience/show";
    }

    @GetMapping("student/{studentId}/workExperiences/{id}/update")
    public String updateWorkExperiences(@PathVariable String studentId,
                                         @PathVariable String id, Model model){
        model.addAttribute("work", workExperienceService.findByStudentIdAndWorkExperienceId(Long.valueOf(studentId), Long.valueOf(id)));

        model.addAttribute("uodList", unitOfDurationService.listAllUod());
        return "student/workExperience/workform";
    }

    @PostMapping("student/{studentId}/workExperience")
    public String saveOrUpdate(@Valid @ModelAttribute("work") WorkExperienceCommand command,BindingResult bindingResult, @PathVariable String studentId){

        if (bindingResult.hasErrors()) {

            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "student/workExperience/workform";
        }
        WorkExperienceCommand savedCommand = workExperienceService.saveWorkExperience(command,Long.valueOf(studentId));
        log.debug("saved student id:" + savedCommand.getStudentProfileId());
        log.debug("saved WorkExperience id:" + savedCommand.getId());

        return "redirect:/student/" + savedCommand.getStudentProfileId() + "/workExperiences/" + savedCommand.getId() + "/show";
    }

    @GetMapping("student/{studentId}/workExperiences/{id}/delete")
    public String deleteWorkExperience(@PathVariable String studentId,
                                @PathVariable String id){

        log.debug("deleting project id:" + id);
        workExperienceService.deleteByIdWorkExperience(Long.valueOf(studentId), Long.valueOf(id));

        return "redirect:/student/" + studentId + "/workExperiences";
    }


}
