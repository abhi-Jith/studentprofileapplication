package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.services.ImageService;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private final ImageService imageService;
    private final StudentProfileService studentProfileService;

    public ImageController(ImageService imageService, StudentProfileService studentProfileService) {
        this.imageService = imageService;
        this.studentProfileService = studentProfileService;
    }

    @GetMapping("student/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("student", studentProfileService.findCommandById(Long.valueOf(id)));

        return "student/imageform";
    }

    @PostMapping("student/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/student/" + id + "/show";
    }
    @GetMapping("student/{id}/studentimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        StudentProfileCommand studentProfileCommand  = studentProfileService.findCommandById(Long.valueOf(id));

        if (studentProfileCommand.getImage() != null) {
            byte[] byteArray = new byte[studentProfileCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : studentProfileCommand.getImage()){
                byteArray[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
