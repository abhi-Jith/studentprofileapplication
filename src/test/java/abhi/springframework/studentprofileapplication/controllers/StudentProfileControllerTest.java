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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class StudentProfileControllerTest {
    @Mock
    StudentProfileService studentProfileService;

    StudentProfileController studentProfileController;

    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentProfileController = new StudentProfileController(studentProfileService);
        mockMvc = MockMvcBuilders.standaloneSetup(studentProfileController).build();
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
    public void testDeleteAction() throws Exception {
        mockMvc.perform(get("/student/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(studentProfileService,times(1)).deleteById(anyLong());
    }

}