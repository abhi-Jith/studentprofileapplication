package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.SkillSetCommand;
import abhi.springframework.studentprofileapplication.domain.SkillSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkillSetCommandToSkillSetTest {
    SkillSetCommandToSkillSet converter;

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "am a test description";
    public static final String SKILL_NAME = "Test Skill title";


    @Before
    public void setUp() throws Exception {
        converter = new SkillSetCommandToSkillSet();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new SkillSetCommand()));
    }


    @Test
    public void convert() throws Exception {
        SkillSetCommand skillSetCommand = new SkillSetCommand();
        skillSetCommand.setId(ID_VALUE);
        skillSetCommand.setDescription(DESCRIPTION);
        skillSetCommand.setSkillName(SKILL_NAME);


        SkillSet skillSet  = converter.convert(skillSetCommand);

        assertNotNull(skillSet);
        assertEquals(ID_VALUE, skillSet.getId());
        assertEquals(DESCRIPTION, skillSet.getDescription());
        assertEquals(SKILL_NAME, skillSet.getSkillName());
    }
}