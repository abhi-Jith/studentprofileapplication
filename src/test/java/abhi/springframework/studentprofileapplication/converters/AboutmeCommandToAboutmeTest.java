package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.AboutmeCommand;
import abhi.springframework.studentprofileapplication.domain.Aboutme;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AboutmeCommandToAboutmeTest {
    public static final Long ID_VALUE = new Long(1L);
    public static final String ABOUTME = "am a test description";
    AboutmeCommandToAboutme converter;

    @Before
    public void setUp() throws Exception {
        converter = new AboutmeCommandToAboutme();

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new AboutmeCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        AboutmeCommand aboutmeCommand = new AboutmeCommand();
        aboutmeCommand.setId(ID_VALUE);
        aboutmeCommand.setAboutMe(ABOUTME);

        //when
        Aboutme aboutme = converter.convert(aboutmeCommand);

        //then
        assertNotNull(aboutme);
        assertEquals(ID_VALUE, aboutme.getId());
        assertEquals(ABOUTME, aboutme.getAboutMe());
    }

}