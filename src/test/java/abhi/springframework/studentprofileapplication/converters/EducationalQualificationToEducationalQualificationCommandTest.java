package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.EducationalQualificationCommand;
import abhi.springframework.studentprofileapplication.domain.EducationalQualification;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EducationalQualificationToEducationalQualificationCommandTest {
    EducationalQualificationToEducationalQualificationCommand converter;
    public static final Long ID_VALUE = new Long(1L);
    public static final String QUALIFICATION = "am a test qualification";
    public static final String INSTITUITION = "Test Instituition";
    public static final String YEARS = "3 Years";

    @Before
    public void setUp() throws Exception {
        converter= new EducationalQualificationToEducationalQualificationCommand();
    }
    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new EducationalQualification()));
    }


    @Test
    public void convert() throws Exception {
        EducationalQualification command = new EducationalQualification();
        command.setYear(YEARS);
        command.setQualification(QUALIFICATION);
        command.setInstitutionName(INSTITUITION);
        command.setId(ID_VALUE);

        EducationalQualificationCommand educationalQualificationCommand = converter.convert(command);

        assertNotNull(educationalQualificationCommand);

        assertEquals(ID_VALUE, educationalQualificationCommand.getId());
        assertEquals(QUALIFICATION, educationalQualificationCommand.getQualification());
        assertEquals(INSTITUITION, educationalQualificationCommand.getInstitutionName());
        assertEquals(YEARS, educationalQualificationCommand.getYear());



    }

}