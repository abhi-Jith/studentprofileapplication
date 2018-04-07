package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.exceptions.NotFoundException;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentProfileControllerTest {
    @Mock
    StudentProfileService studentProfileService;

    StudentProfileController studentProfileController;

    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentProfileController = new StudentProfileController(studentProfileService);
        mockMvc = MockMvcBuilders.standaloneSetup(studentProfileController).setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test
    public void testGetStudentProfile() throws Exception {
        StudentProfile studentProfile= new StudentProfile();
        studentProfile.setId(1L);

        when(studentProfileService.findById(anyLong())).thenReturn(studentProfile);

        mockMvc.perform(get("/student/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/show"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    public void testStudentProfileNotFound() throws Exception {
        when(studentProfileService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/student/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }
    @Test
    public void testStudentProfileNumberFormatException() throws Exception {
        when(studentProfileService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/student/asd/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    public void newStudentForm() throws Exception {
        StudentProfileCommand command = new StudentProfileCommand();

        mockMvc.perform(get("/student/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentform"))
                .andExpect(model().attributeExists("student"));
    }
    @Test
    public void testPostNewStudentFormValidationFail() throws Exception {
        StudentProfileCommand command = new StudentProfileCommand();
        command.setId(2L);

        when(studentProfileService.saveStudentProfileCommand(any())).thenReturn(command);

        mockMvc.perform(post("/student")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")

        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("student"))
                .andExpect(view().name("student/studentform"));
    }
    @Test
    public void testPostNewStudentForm() throws Exception {
        StudentProfileCommand command = new StudentProfileCommand();
        command.setId(2L);

        when(studentProfileService.saveStudentProfileCommand(any())).thenReturn(command);

        mockMvc.perform(post("/student")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("firstName", "some string")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/student/2/show"));

    }

    @Test
    public void showById() throws Exception {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(1L);


        when(studentProfileService.findById(anyLong())).thenReturn(studentProfile);

        mockMvc.perform(get("/student/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        StudentProfileCommand command = new StudentProfileCommand();
        command.setId(2L);

        when(studentProfileService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/student/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentform"))
                .andExpect(model().attributeExists("student"));
    }
    @Test
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/student/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(studentProfileService,times(1)).deleteById(anyLong());
    }

}