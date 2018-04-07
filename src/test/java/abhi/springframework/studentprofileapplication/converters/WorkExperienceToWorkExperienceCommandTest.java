package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.WorkExperienceCommand;
import abhi.springframework.studentprofileapplication.domain.UnitOfDuration;
import abhi.springframework.studentprofileapplication.domain.WorkExperience;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkExperienceToWorkExperienceCommandTest {
    WorkExperienceToWorkExperienceCommand converter;
    public static final Long ID_VALUE = new Long(1L);
    public static final String COMPANY = "am a test company";
    public static final String ADDRESS = "am a test address";
    public static final String POS_NAME = "am a test position name";
    public static final String UOD = "am a test UOD";

    @Before
    public void setUp() throws Exception {
        converter = new WorkExperienceToWorkExperienceCommand(new UnitOfDurationToUnitOfDurationCommand());
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new WorkExperience()));
    }


    @Test
    public void convert() throws Exception {
        WorkExperience workExperience  = new WorkExperience();
        workExperience.setId(ID_VALUE);
        workExperience.setCompanyName(COMPANY);
        workExperience.setPositionName(POS_NAME);
        workExperience.setCompanyAddress(ADDRESS);

        UnitOfDuration uom = new UnitOfDuration();
        uom.setUod(UOD);

        workExperience.setUnitOfDuration(uom);

        WorkExperienceCommand workExperienceCommand = converter.convert(workExperience);

        assertNotNull(workExperienceCommand);
        assertEquals(ID_VALUE, workExperienceCommand.getId());
        assertEquals(UOD, workExperienceCommand.getUnitOfDuration().getUod());
        assertEquals(ADDRESS,workExperienceCommand.getCompanyAddress());
        assertEquals(COMPANY,workExperienceCommand.getCompanyName());

    }


}