package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class StudentProfileControllerTest {
    @Mock
    StudentProfileService studentProfileService;

    StudentProfileController studentProfileController;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentProfileController = new StudentProfileController(studentProfileService);
    }

    @Test
    public void showById() throws Exception {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(studentProfileController).build();

        when(studentProfileService.findById(anyLong())).thenReturn(studentProfile);

        mockMvc.perform(get("/student/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/show"));
    }

}