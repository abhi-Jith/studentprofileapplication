package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.ProjectsCommand;
import abhi.springframework.studentprofileapplication.domain.Projects;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectsCommandToProjectsTest {
    ProjectsCommandToProjects converter;

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "am a test description";
    public static final String PROJECTTITLE = "Test Project title";
    public static final String TECH = "SPRING";

    @Before
    public void setUp() throws Exception {
        converter = new ProjectsCommandToProjects();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new ProjectsCommand()));
    }


    @Test
    public void convert() throws Exception {
        ProjectsCommand command = new ProjectsCommand();
        command.setId(ID_VALUE);
        command.setProjectDescription(DESCRIPTION);
        command.setTechnologiesUsed(TECH);
        command.setProjectTitle(PROJECTTITLE);

        Projects projects  = converter.convert(command);

        assertNotNull(projects);
        assertEquals(ID_VALUE, projects.getId());
        assertEquals(DESCRIPTION, projects.getProjectDescription());
        assertEquals(PROJECTTITLE, projects.getProjectTitle());
        assertEquals(TECH, projects.getTechnologiesUsed());

    }
}