package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class StudentProfileServiceImplTest {

    StudentProfileServiceImpl studentProfileService;

    @Mock
    StudentProfileRepository studentProfileRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentProfileService = new StudentProfileServiceImpl(studentProfileRepository);
    }

    @Test
    public void getStudentProfile() throws Exception {
        StudentProfile studentProfile = new StudentProfile();

        HashSet studentData = new HashSet();
        studentData.add(studentProfile);

        when(studentProfileService.getStudentProfile()).thenReturn(studentData);
        Set<StudentProfile> studentProfiles = studentProfileService.getStudentProfile();

        assertEquals(studentProfiles.size(),1);
        verify(studentProfileRepository, times(1)).findAll();
    }

}