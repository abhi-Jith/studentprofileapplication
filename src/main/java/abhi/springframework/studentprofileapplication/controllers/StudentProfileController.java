package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentProfileController {
    StudentProfileService studentProfileService;

    @Autowired
    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @RequestMapping("/student/show/{id}")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("student",studentProfileService.findById(new Long(id)));
        return "student/show";
    }
}
