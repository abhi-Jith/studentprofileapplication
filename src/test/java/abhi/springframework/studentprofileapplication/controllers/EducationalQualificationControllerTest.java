package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.EducationalQualificationCommand;
import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.services.EducationalQualificationService;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EducationalQualificationControllerTest {

    @Mock
    StudentProfileService studentProfileService;
    EducationalQualificationController controller;
    @Mock
    EducationalQualificationService educationalQualificationService;
    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new EducationalQualificationController(studentProfileService,educationalQualificationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listEducationalQualifications() throws Exception {
        StudentProfileCommand command = new StudentProfileCommand();
        command.setId(2L);

        when(studentProfileService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/student/1/educationalQualifications")).andExpect(status().isOk())
                .andExpect(view().name("student/educationalQualification/qualificationList"))
                .andExpect(model().attributeExists("student"));

        verify(studentProfileService,times(1)).findCommandById(anyLong());
    }

    @Test
    public  void testShowListEducationQualifications() throws Exception{
        EducationalQualificationCommand educationalQualificationCommand = new EducationalQualificationCommand();

        //when
        when(educationalQualificationService.findByStudentIdAndEducationalId(anyLong(), anyLong())).thenReturn(educationalQualificationCommand);

        //then
        mockMvc.perform(get("/student/1/educationalQualification/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/educationalQualification/show"))
                .andExpect(model().attributeExists("educational"));
    }

}