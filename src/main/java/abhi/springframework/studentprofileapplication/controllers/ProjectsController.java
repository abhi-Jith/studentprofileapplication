package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.ProjectsCommand;
import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.services.ProjectsService;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ProjectsController {
    private final StudentProfileService studentProfileService;
    private final ProjectsService projectsService;

    public ProjectsController(StudentProfileService studentProfileService, ProjectsService projectsService) {
        this.studentProfileService = studentProfileService;
        this.projectsService = projectsService;
    }

    @GetMapping("/student/{studentId}/projects")
    public String listProjects(@PathVariable String studentId, Model model){
        log.debug("Getting Projects for studentProfile id:" +studentId);
        model.addAttribute("student",studentProfileService.findCommandById(Long.valueOf(studentId)));
        return "student/projects/projectList";
    }

    @GetMapping("student/{studentId}/projects/new")
    public String newProject(@PathVariable String studentId, Model model) {
        StudentProfileCommand studentProfileCommand = studentProfileService.findCommandById(Long.valueOf(studentId));

        ProjectsCommand projectsCommand = new ProjectsCommand();
        projectsCommand.setStudentProfileId(Long.valueOf(studentId));
        model.addAttribute("project", projectsCommand);
        return "student/projects/projectform";

    }

    @GetMapping("student/{studentId}/projects/{id}/show")
    public String showListProjects(@PathVariable String studentId,
                                                  @PathVariable String id, Model model){
        model.addAttribute("project", projectsService.findByStudentIdAndProjectId(Long.valueOf(studentId), Long.valueOf(id)));
        return "student/projects/show";
    }

    @GetMapping("student/{studentId}/projects/{id}/update")
    public String updateProject(@PathVariable String studentId,
                                         @PathVariable String id, Model model){

        model.addAttribute("project", projectsService.findByStudentIdAndProjectId(Long.valueOf(studentId), Long.valueOf(id)));

        log.debug("AM IN THE updateProject FUNCTION");
        return "student/projects/projectform";
    }

    @PostMapping("student/{studentId}/project")
    public String saveOrUpdate(@ModelAttribute ProjectsCommand command, @PathVariable String studentId){
        log.debug("AM IN THE SAVEORUPDATE FUNCTION");
        ProjectsCommand savedCommand = projectsService.saveProjectCommand(command,Long.valueOf(studentId));

        log.debug("saved student id:" + savedCommand.getStudentProfileId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/student/" + savedCommand.getStudentProfileId() + "/projects/" + savedCommand.getId() + "/show";
    }

    @GetMapping("student/{studentId}/projects/{id}/delete")
    public String deleteProject(@PathVariable String studentId,
                                                 @PathVariable String id){

        log.debug("deleting project id:" + id);
        projectsService.deleteById(Long.valueOf(studentId), Long.valueOf(id));

        return "redirect:/student/" + studentId + "/projects";
    }


}
