package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.UnitOfDurationCommand;

import abhi.springframework.studentprofileapplication.domain.UnitOfDuration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UnitOfDurationCommandToUnitOfDurationTest {
    UnitOfDurationCommandToUnitOfDuration converter;
    public static final Long ID_VALUE = new Long(1L);
    public static final String UOD = "am a test UOD";



    @Before
    public void setUp() throws Exception {
        converter = new UnitOfDurationCommandToUnitOfDuration();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfDurationCommand()));
    }


    @Test
    public void convert() throws Exception {
        UnitOfDurationCommand unitOfDurationCommand  = new UnitOfDurationCommand();
        unitOfDurationCommand.setId(ID_VALUE);
        unitOfDurationCommand.setUod(UOD);


        UnitOfDuration unitOfDuration  = converter.convert(unitOfDurationCommand);

        assertNotNull(unitOfDuration);
        assertEquals(ID_VALUE, unitOfDuration.getId());
        assertEquals(UOD, unitOfDuration.getUod());

    }

}