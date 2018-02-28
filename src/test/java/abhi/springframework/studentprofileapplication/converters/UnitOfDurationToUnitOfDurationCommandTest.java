package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.UnitOfDurationCommand;
import abhi.springframework.studentprofileapplication.domain.UnitOfDuration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UnitOfDurationToUnitOfDurationCommandTest {
    UnitOfDurationToUnitOfDurationCommand converter;
    public static final Long ID_VALUE = new Long(1L);
    public static final String UOD = "am a test UOD";



    @Before
    public void setUp() throws Exception {
        converter = new UnitOfDurationToUnitOfDurationCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfDuration()));
    }


    @Test
    public void convert() throws Exception {
        UnitOfDuration unitOfDuration = new UnitOfDuration();
        unitOfDuration.setId(ID_VALUE);
        unitOfDuration.setUod(UOD);


        UnitOfDurationCommand unitOfDurationCommand  = converter.convert(unitOfDuration);

        assertNotNull(unitOfDurationCommand);
        assertEquals(ID_VALUE, unitOfDurationCommand.getId());
        assertEquals(UOD, unitOfDurationCommand.getUod());

    }
}