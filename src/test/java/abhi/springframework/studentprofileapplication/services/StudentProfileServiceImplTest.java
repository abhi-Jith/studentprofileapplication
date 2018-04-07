package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.converters.StudentProfileCommandToStudentProfile;
import abhi.springframework.studentprofileapplication.converters.StudentProfileToStudentProfileCommand;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.exceptions.NotFoundException;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class StudentProfileServiceImplTest {

    StudentProfileServiceImpl studentProfileService;

    @Mock
    StudentProfileRepository studentProfileRepository;

    @Mock
    StudentProfileCommandToStudentProfile studentProfileCommandToStudentProfile;

    @Mock
    StudentProfileToStudentProfileCommand studentProfileToStudentProfileCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentProfileService = new StudentProfileServiceImpl(studentProfileRepository,studentProfileCommandToStudentProfile,studentProfileToStudentProfileCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(1L);
        Optional<StudentProfile> studentProfileOptional = Optional.of(studentProfile);

        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);

        StudentProfile studentProfileReturned = studentProfileService.findById(1L);

        assertNotNull("Null student Profile returned", studentProfileReturned);
        verify(studentProfileRepository, Mockito.times(1)).findById(anyLong());
        verify(studentProfileRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void getStudentByIdTestNotFound() throws Exception {

        Optional<StudentProfile> studentProfileOptional= Optional.empty();

        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);

        StudentProfile studentProfileReturned = studentProfileService.findById(1L);
    }
    @Test
    public void getStudentProfileCommandByIdTest() throws Exception {
        StudentProfile studentProfile= new StudentProfile();
        studentProfile.setId(1L);
        Optional<StudentProfile> studentProfileOptional = Optional.of(studentProfile);

        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);

        StudentProfileCommand studentProfileCommand = new StudentProfileCommand();
        studentProfileCommand.setId(1L);

        when(studentProfileToStudentProfileCommand.convert(any())).thenReturn(studentProfileCommand);

        StudentProfileCommand commandById = studentProfileService.findCommandById(1L);

        assertNotNull("Null student returned", commandById);
        verify(studentProfileRepository, times(1)).findById(anyLong());
        verify(studentProfileRepository, never()).findAll();
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
    @Test
    public void testDeleteById() throws Exception {
        Long deleteId = Long.valueOf(2L);
        studentProfileService.deleteById(deleteId);

        verify(studentProfileRepository, Mockito.times(1)).deleteById(anyLong());
    }


}