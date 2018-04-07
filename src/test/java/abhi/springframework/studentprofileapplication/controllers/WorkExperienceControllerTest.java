package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import abhi.springframework.studentprofileapplication.services.UnitOfDurationService;
import abhi.springframework.studentprofileapplication.services.WorkExperienceService;
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

public class WorkExperienceControllerTest {

    @Mock
    StudentProfileService studentProfileService;
    WorkExperienceController controller;
    MockMvc mockMvc;
    @Mock
    WorkExperienceService  workExperienceService;
    @Mock
    UnitOfDurationService unitOfDurationService;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new WorkExperienceController(studentProfileService,workExperienceService,unitOfDurationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listEducationalQualifications() throws Exception {
        StudentProfileCommand command = new StudentProfileCommand();
        command.setId(2L);

        when(studentProfileService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/student/1/workExperiences")).andExpect(status().isOk())
                .andExpect(view().name("student/workExperience/workList"))
                .andExpect(model().attributeExists("student"));

        verify(studentProfileService,times(1)).findCommandById(anyLong());
    }
}