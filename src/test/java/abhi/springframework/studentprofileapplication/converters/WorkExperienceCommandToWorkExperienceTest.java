package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.UnitOfDurationCommand;
import abhi.springframework.studentprofileapplication.commands.WorkExperienceCommand;
import abhi.springframework.studentprofileapplication.domain.WorkExperience;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class WorkExperienceCommandToWorkExperienceTest {
    WorkExperienceCommandToWorkExperience converter;
    public static final Long ID_VALUE = new Long(1L);
    public static final String COMPANY = "am a test company";
    public static final String ADDRESS = "am a test address";
    public static final String POS_NAME = "am a test position name";
    public static final String UOD = "am a test UOD";

    @Before
    public void setUp() throws Exception {
        converter = new WorkExperienceCommandToWorkExperience(new UnitOfDurationCommandToUnitOfDuration());
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new WorkExperienceCommand()));
    }


    @Test
    public void convert() throws Exception {
        WorkExperienceCommand workExperienceCommand  = new WorkExperienceCommand();
        workExperienceCommand.setId(ID_VALUE);
        workExperienceCommand.setCompanyName(COMPANY);
        workExperienceCommand.setPositionName(POS_NAME);
        workExperienceCommand.setCompanyAddress(ADDRESS);

        UnitOfDurationCommand uomCommand = new UnitOfDurationCommand();
        uomCommand.setUod(UOD);

        workExperienceCommand.setUnitOfDuration(uomCommand);

        WorkExperience workExperience = converter.convert(workExperienceCommand);

        assertNotNull(workExperience);
        assertEquals(ID_VALUE, workExperience.getId());
        assertEquals(UOD, workExperience.getUnitOfDuration().getUod());
        assertEquals(COMPANY,workExperience.getCompanyName());

    }

}