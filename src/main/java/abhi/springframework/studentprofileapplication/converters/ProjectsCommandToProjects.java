package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.ProjectsCommand;
import abhi.springframework.studentprofileapplication.domain.Projects;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
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
        if(projectsCommand.getStudentProfileId() != null){
            StudentProfile studentProfile = new StudentProfile();
            studentProfile.setId(projectsCommand.getStudentProfileId());
            projects.setStudentProfile(studentProfile);
            studentProfile.addProjects(projects);
        }
        projects.setProjectDescription(projectsCommand.getProjectDescription());
        projects.setProjectTitle(projectsCommand.getProjectTitle());
        projects.setTechnologiesUsed(projectsCommand.getTechnologiesUsed());
        return projects;
    }
}
