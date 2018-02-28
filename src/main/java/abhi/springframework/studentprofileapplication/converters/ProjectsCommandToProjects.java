package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.ProjectsCommand;
import abhi.springframework.studentprofileapplication.domain.Projects;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ProjectsCommandToProjects implements Converter<ProjectsCommand,Projects> {
    @Synchronized
    @Nullable
    @Override
    public Projects convert(ProjectsCommand projectsCommand) {
        if (projectsCommand == null){
            return null;
        }
        final Projects projects = new Projects();
        projects.setId(projectsCommand.getId());
        projects.setProjectDescription(projectsCommand.getProjectDescription());
        projects.setProjectTitle(projectsCommand.getProjectTitle());
        projects.setTechnologiesUsed(projectsCommand.getTechnologiesUsed());
        return projects;
    }
}
