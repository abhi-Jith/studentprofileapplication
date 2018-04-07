package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.*;
import abhi.springframework.studentprofileapplication.domain.CodingLevel;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentProfileCommandToStudentProfileTest {
    public static final Long STUDENT_ID = 1L;
    public static final Integer SSN = Integer.valueOf("5");
    public static final String  NUM = "305-567-5676";
    public static final String GENDER = "MALE";
    public static final String ADDRESS = "My STUDENT ";
    public static final String EMAIL = "Directions";
    public static final CodingLevel CODING_LEVEL = CodingLevel.MODERATE;

    public static final String DOB = "09-08-87";
    public static final String FIRST_NAME = "Sowmya";
    public static final String LAST_NAME= "Shamarthi";
    public static final Long SKILL_ID = 2L;
    public static final Long SKILL_ID_2 = 3L;
    public static final Long EDU_QUALIF_ID = 4L;
    public static final Long EDU_QUALIF_ID_2 = 9L;
    public static final Long ABOUT_ME_ID = 5L;

    public static final Long PROJ_ID = 8L;
    public static final Long PROJ_ID_2 = 7L;
    public static final Long WORK_ID = 9L;
    public static final Long WORK_ID_2 = 1L;


    StudentProfileCommandToStudentProfile converter;


    @Before
    public void setUp() throws Exception {
        converter = new StudentProfileCommandToStudentProfile(new AboutmeCommandToAboutme(),
                new EducationalQualificationCommandToEducationalQualification(),new ProjectsCommandToProjects(),
                new SkillSetCommandToSkillSet(),
                new WorkExperienceCommandToWorkExperience(new UnitOfDurationCommandToUnitOfDuration()));
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new StudentProfileCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        StudentProfileCommand studentProfileCommand = new StudentProfileCommand();
        studentProfileCommand.setId(STUDENT_ID);
        studentProfileCommand.setFirstName(FIRST_NAME);
        studentProfileCommand.setAddress(ADDRESS);
        studentProfileCommand.setDob(DOB);
        studentProfileCommand.setEmailId(EMAIL);
        studentProfileCommand.setGender(GENDER);
        studentProfileCommand.setMobileNumber(NUM);
        studentProfileCommand.setSsn(SSN);
        studentProfileCommand.setLastName(LAST_NAME);
        studentProfileCommand.setCodingLevel(CODING_LEVEL);

        AboutmeCommand aboutmeCommand = new AboutmeCommand();
        aboutmeCommand.setId(ABOUT_ME_ID);

        studentProfileCommand.setAboutme(aboutmeCommand);

        EducationalQualificationCommand command = new EducationalQualificationCommand();
        command.setId(EDU_QUALIF_ID);

        EducationalQualificationCommand command2 = new EducationalQualificationCommand();
        command2.setId(EDU_QUALIF_ID_2);

        studentProfileCommand.getEducationalQualifications().add(command);
        studentProfileCommand.getEducationalQualifications().add(command2);

        WorkExperienceCommand workExperienceCommand = new WorkExperienceCommand();
        workExperienceCommand.setId(WORK_ID);

        WorkExperienceCommand workExperienceCommand2 = new WorkExperienceCommand();
        workExperienceCommand2.setId(WORK_ID_2);

        studentProfileCommand.getWorkExperiences().add(workExperienceCommand);
        studentProfileCommand.getWorkExperiences().add(workExperienceCommand2);

        ProjectsCommand projectsCommand = new ProjectsCommand();
        projectsCommand.setId(PROJ_ID);

        ProjectsCommand projectsCommand1 = new ProjectsCommand();
        projectsCommand1.setId(PROJ_ID_2);

        studentProfileCommand.getProjects().add(projectsCommand);
        studentProfileCommand.getProjects().add(projectsCommand1);

        SkillSetCommand skillSetCommand = new SkillSetCommand();
        skillSetCommand.setId(SKILL_ID);
        SkillSetCommand skillSetCommand1 = new SkillSetCommand();
        skillSetCommand1.setId(SKILL_ID_2);
        studentProfileCommand.getSkillSets().add(skillSetCommand);
        studentProfileCommand.getSkillSets().add(skillSetCommand1);

        //when
        StudentProfile studentProfile = converter.convert(studentProfileCommand);

        assertNotNull(studentProfile);
        assertEquals(STUDENT_ID, studentProfile.getId());
        assertEquals(FIRST_NAME, studentProfile.getFirstName());
        assertEquals(LAST_NAME, studentProfile.getLastName());
        assertEquals(ADDRESS, studentProfile.getAddress());
        assertEquals(NUM, studentProfile.getMobileNumber());
        assertEquals(GENDER, studentProfile.getGender());
        assertEquals(EMAIL, studentProfile.getEmailId());
        assertEquals(DOB, studentProfile.getDob());
        assertEquals(CODING_LEVEL, studentProfile.getCodingLevel());
        assertEquals(ABOUT_ME_ID, studentProfile.getAboutme().getId());
        assertEquals(2, studentProfile.getEducationalQualifications().size());
        assertEquals(2, studentProfile.getProjects().size());
        assertEquals(2, studentProfile.getWorkExperiences().size());
        assertEquals(2, studentProfile.getSkillSets().size());
    }

}