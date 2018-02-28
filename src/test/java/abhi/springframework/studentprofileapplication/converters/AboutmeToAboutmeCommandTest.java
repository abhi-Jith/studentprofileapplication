package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.AboutmeCommand;
import abhi.springframework.studentprofileapplication.domain.Aboutme;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AboutmeToAboutmeCommandTest {
    public static final Long ID_VALUE = new Long(1L);
    public static final String ABOUTME = "am a test description";
    AboutmeToAboutmeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new AboutmeToAboutmeCommand();

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Aboutme()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Aboutme aboutme = new Aboutme();
        aboutme.setId(ID_VALUE);
        aboutme.setAboutMe(ABOUTME);

        //when
        AboutmeCommand aboutmeCommand = converter.convert(aboutme);

        //then
        assertNotNull(aboutmeCommand);
        assertEquals(ID_VALUE, aboutmeCommand.getId());
        assertEquals(ABOUTME, aboutmeCommand.getAboutMe());
    }

}