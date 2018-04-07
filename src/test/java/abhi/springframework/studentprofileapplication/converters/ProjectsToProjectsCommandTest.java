package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.ProjectsCommand;
import abhi.springframework.studentprofileapplication.domain.Projects;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ProjectsToProjectsCommandTest {
    ProjectsToProjectsCommand converter;

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "am a test description";
    public static final String PROJECTTITLE = "Test Project title";
    public static final String TECH = "SPRING";

    @Before
    public void setUp() throws Exception {
        converter = new ProjectsToProjectsCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Projects()));
    }


    @Test
    public void convert() throws Exception {
        Projects projects = new Projects();
        projects.setId(ID_VALUE);
        projects.setProjectDescription(DESCRIPTION);
        projects.setTechnologiesUsed(TECH);
        projects.setProjectTitle(PROJECTTITLE);

        ProjectsCommand projectsCommand  = converter.convert(projects);

        assertNotNull(projectsCommand);
        assertEquals(ID_VALUE, projectsCommand.getId());
        assertEquals(DESCRIPTION, projectsCommand.getProjectDescription());
        assertEquals(PROJECTTITLE, projectsCommand.getProjectTitle());
        assertEquals(TECH, projectsCommand.getTechnologiesUsed());

    }
}