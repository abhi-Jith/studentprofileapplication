package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.exceptions.NotFoundException;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class StudentProfileController {
    public final StudentProfileService studentProfileService;

    @Autowired
    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @GetMapping("/student/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("student",studentProfileService.findById(new Long(id)));
        return "student/show";
    }

    @GetMapping("student/new")
    public String newStudentProfile(Model model){

        model.addAttribute("student", new StudentProfileCommand());
        return "student/studentform";
    }

    @GetMapping("student/{id}/update")
    public String updateStudentProfile(@PathVariable String id, Model model){

        model.addAttribute("student", studentProfileService.findCommandById(Long.valueOf(id)));
        return  "student/studentform";
    }

    @PostMapping("student")
    public String saveOrUpdate(@Valid @ModelAttribute("student") StudentProfileCommand command, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                                log.debug(objectError.toString());
            });

            return "student/studentform";
        }

        StudentProfileCommand savedCommand = studentProfileService.saveStudentProfileCommand(command);

        return "redirect:/student/"+ savedCommand.getId()+"/show" ;
    }

    @GetMapping("student/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        studentProfileService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
