package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.ProjectsCommand;
import abhi.springframework.studentprofileapplication.converters.ProjectsCommandToProjects;
import abhi.springframework.studentprofileapplication.converters.ProjectsToProjectsCommand;
import abhi.springframework.studentprofileapplication.domain.Projects;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Service
public class ProjectsServiceImpl implements ProjectsService {
    private final ProjectsToProjectsCommand projectsToProjectsCommand;
    private final StudentProfileRepository studentProfileRepository;
    private final ProjectsCommandToProjects projectsCommandToProjects;

    public ProjectsServiceImpl(ProjectsToProjectsCommand projectsToProjectsCommand, StudentProfileRepository studentProfileRepository, ProjectsCommandToProjects projectsCommandToProjects) {
        this.projectsToProjectsCommand = projectsToProjectsCommand;
        this.studentProfileRepository = studentProfileRepository;
        this.projectsCommandToProjects = projectsCommandToProjects;
    }

    @Override
    public ProjectsCommand findByStudentIdAndProjectId(Long studentId, Long projectId) {
        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        if (!studentProfileOptional.isPresent()) {
            //todo impl error handling
            log.error("student id not found. Id:" + studentId);
        }
        StudentProfile studentProfile = studentProfileOptional.get();

        Optional<ProjectsCommand> projectsCommandOptional = studentProfile.getProjects().stream()
                .filter(project -> project.getId().equals(projectId))
                .map(project -> projectsToProjectsCommand.convert(project)).findFirst();

        if (!projectsCommandOptional.isPresent()) {
            //todo error handling
            log.error("projects id not present:" + projectId);
        }
        return projectsCommandOptional.get();
    }

    @Override
    @Transactional
    public ProjectsCommand saveProjectCommand(ProjectsCommand command, Long studentId) {
        //log.debug("first line of saveProject");
        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        // log.debug("second line of saveProject");
        if (!studentProfileOptional.isPresent()) {
            //   log.debug("StudentProfile for project command not present");
            //todo toss error if not found!
            log.error("Student not found for id: " + command.getStudentProfileId());
            return new ProjectsCommand();
        } else {
            log.debug("Entered Else for saveProject");
            StudentProfile studentProfile = studentProfileOptional.get();

            Optional<Projects> projectsOptional = studentProfile
                    .getProjects()
                    .stream()
                    .filter(project -> project.getId().equals(command.getId()))
                    .findFirst();

            if (projectsOptional.isPresent()) {
                Projects projectsFound = projectsOptional.get();
                projectsFound.setProjectTitle(command.getProjectTitle());
                projectsFound.setTechnologiesUsed(command.getTechnologiesUsed());
                projectsFound.setProjectDescription(command.getProjectDescription()); //todo address this
            } else {
                //add new Ingredient
                Projects projects = projectsCommandToProjects.convert(command);
                projects.setStudentProfile(studentProfile);
                studentProfile.addProjects(projects);

            }

            StudentProfile savedStudentProfile = studentProfileRepository.save(studentProfile);

            Optional<Projects> savedProjectsOptional = savedStudentProfile.getProjects().stream()
                    .filter(proj -> proj.getId().equals(command.getId())).findFirst();

            if (!savedProjectsOptional.isPresent()) {
                savedProjectsOptional = savedStudentProfile.getProjects().stream()
                        .filter(proj -> proj.getProjectTitle().equals(command.getProjectTitle()))
                        .filter(proj -> proj.getTechnologiesUsed().equals(command.getTechnologiesUsed()))
                        .filter(proj -> proj.getProjectDescription().equals(command.getProjectDescription())).findFirst();
            }
            return projectsToProjectsCommand.convert(savedProjectsOptional.get());
        }
    }

    @Override
    public void deleteById(Long studentId, Long idToDelete) {
        log.debug("Deleting project: " + studentId + ":" + idToDelete);

        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        if (studentProfileOptional.isPresent()) {
            StudentProfile studentProfile = studentProfileOptional.get();
            log.debug("found student profile");

            Optional<Projects> projectsOptional = studentProfile
                    .getProjects()
                    .stream()
                    .filter(project -> project.getId().equals(idToDelete))
                    .findFirst();

            if (projectsOptional.isPresent()) {
                log.debug("found project");
                Projects projectToDelete = projectsOptional.get();
                projectToDelete.setStudentProfile(null);
                studentProfile.getProjects().remove(projectsOptional.get());
                studentProfileRepository.save(studentProfile);
            }

        } else {
            log.debug("Student Id Not found. Id:" + studentId);
        }
    }
}

