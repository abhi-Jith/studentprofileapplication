package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private StudentProfileService studentProfileService;

    public IndexController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }


    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("studentProfiles",studentProfileService.getStudentProfile());
        return "index";
    }
}
