package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.EducationalQualificationCommand;
import abhi.springframework.studentprofileapplication.domain.EducationalQualification;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EducationalQualificationCommandToEducationalQualificationTest {
    EducationalQualificationCommandToEducationalQualification converter;

    public static final Long ID_VALUE = new Long(1L);
    public static final String QUALIFICATION = "am a test qualification";
    public static final String INSTITUITION = "Test Instituition";
    public static final String YEARS = "3 Years";

    @Before
    public void setUp() throws Exception {
        converter = new EducationalQualificationCommandToEducationalQualification();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new EducationalQualificationCommand()));
    }


    @Test
    public void convert() throws Exception {
        EducationalQualificationCommand command = new EducationalQualificationCommand();
        command.setYear(YEARS);
        command.setQualification(QUALIFICATION);
        command.setInstitutionName(INSTITUITION);
        command.setId(ID_VALUE);

        EducationalQualification educationalQualification = converter.convert(command);

        assertNotNull(educationalQualification);
        assertEquals(ID_VALUE, educationalQualification.getId());
        assertEquals(QUALIFICATION, educationalQualification.getQualification());
        assertEquals(INSTITUITION, educationalQualification.getInstitutionName());
        assertEquals(YEARS, educationalQualification.getYear());

    }
}