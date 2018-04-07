package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.SkillSetCommand;
import abhi.springframework.studentprofileapplication.domain.SkillSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkillSetToSkillSetCommandTest {
    SkillSetToSkillSetCommand converter;

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "am a test description";
    public static final String SKILL_NAME = "Test Skill title";


    @Before
    public void setUp() throws Exception {
        converter = new SkillSetToSkillSetCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new SkillSet()));
    }


    @Test
    public void convert() throws Exception {
        SkillSet skillSet = new SkillSet();
        skillSet.setId(ID_VALUE);
        skillSet.setDescription(DESCRIPTION);
        skillSet.setSkillName(SKILL_NAME);


        SkillSetCommand skillSetCommand  = converter.convert(skillSet);

        assertNotNull(skillSetCommand);
        assertEquals(ID_VALUE, skillSetCommand.getId());
        assertEquals(DESCRIPTION, skillSetCommand.getDescription());
        assertEquals(SKILL_NAME, skillSetCommand.getSkillName());
    }
}