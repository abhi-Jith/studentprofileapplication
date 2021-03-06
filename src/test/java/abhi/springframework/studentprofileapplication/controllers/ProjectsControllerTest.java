package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.services.ProjectsService;
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

public class ProjectsControllerTest {
    @Mock
    StudentProfileService studentProfileService;
    ProjectsController controller;
    MockMvc mockMvc;
    @Mock
    ProjectsService projectsService;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new ProjectsController(studentProfileService,projectsService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listEducationalQualifications() throws Exception {
        StudentProfileCommand command = new StudentProfileCommand();
        command.setId(2L);

        when(studentProfileService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/student/1/projects")).andExpect(status().isOk())
                .andExpect(view().name("student/projects/projectList"))
                .andExpect(model().attributeExists("student"));

        verify(studentProfileService,times(1)).findCommandById(anyLong());
    }

}