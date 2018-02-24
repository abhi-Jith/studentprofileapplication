package abhi.springframework.studentprofileapplication.controllers;

import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.services.StudentProfileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest{
    IndexController indexController;
    @Mock
    StudentProfileService studentProfileService;
    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController= new IndexController(studentProfileService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() throws Exception {
        //given
        Set<StudentProfile>studentProfileSet = new HashSet<>();
        studentProfileSet.add(new StudentProfile());
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(1L);
        studentProfileSet.add(studentProfile);

        //when
        ArgumentCaptor<Set<StudentProfile>> argumentCaptor =ArgumentCaptor.forClass(Set.class);
        when(studentProfileService.getStudentProfile()).thenReturn(studentProfileSet);

        //then
        String viewName= indexController.getIndexPage(model);
        assertEquals("index",viewName);
        verify(studentProfileService,times(1)).getStudentProfile();
        verify(model,times(1)).addAttribute(eq("studentProfiles"),argumentCaptor.capture());

        Set<StudentProfile> capturedSet = argumentCaptor.getValue();

        assertEquals(2, capturedSet.size());
    }

}