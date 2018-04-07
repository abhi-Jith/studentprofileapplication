package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.ProjectsCommand;
import org.springframework.stereotype.Service;

@Service
public interface ProjectsService {
    ProjectsCommand saveProjectCommand(ProjectsCommand command, Long studentId);
    ProjectsCommand findByStudentIdAndProjectId(Long studentId, Long projectId);
    void deleteById(Long studentId, Long idToDelete);
}
