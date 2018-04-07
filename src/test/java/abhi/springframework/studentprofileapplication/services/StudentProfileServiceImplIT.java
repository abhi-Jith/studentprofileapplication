package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.converters.StudentProfileCommandToStudentProfile;
import abhi.springframework.studentprofileapplication.converters.StudentProfileToStudentProfileCommand;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentProfileServiceImplIT {
    public static final String NAME = "Mathew";

    @Autowired
    StudentProfileService studentProfileService;

    @Autowired
    StudentProfileRepository studentProfileRepository;

    @Autowired
    StudentProfileCommandToStudentProfile studentProfileCommandToStudentProfile;

    @Autowired
    StudentProfileToStudentProfileCommand studentProfileToStudentProfileCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<StudentProfile> studentProfiles = studentProfileRepository.findAll();
        StudentProfile testStudentProfile = studentProfiles.iterator().next();

        StudentProfileCommand testStudentProfileCommand = studentProfileToStudentProfileCommand.convert(testStudentProfile);

        //when
        testStudentProfileCommand.setFirstName(NAME);
        StudentProfileCommand savedStudentProfileCommand = studentProfileService.saveStudentProfileCommand(testStudentProfileCommand);

        //then
        assertEquals(NAME,savedStudentProfileCommand.getFirstName());
        assertEquals(testStudentProfile.getId(), savedStudentProfileCommand.getId());
        assertEquals(testStudentProfile.getProjects().size(), savedStudentProfileCommand.getProjects().size());
        assertEquals(testStudentProfile.getWorkExperiences().size(), savedStudentProfileCommand.getWorkExperiences().size());
    }

}