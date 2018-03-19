//package abhi.springframework.studentprofileapplication.services;
//
//import abhi.springframework.studentprofileapplication.commands.ProjectsCommand;
//import abhi.springframework.studentprofileapplication.converters.ProjectsCommandToProjects;
//import abhi.springframework.studentprofileapplication.converters.ProjectsToProjectsCommand;
//import abhi.springframework.studentprofileapplication.domain.Projects;
//import abhi.springframework.studentprofileapplication.domain.StudentProfile;
//import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class ProjectsServiceImplTest {
//    private final ProjectsToProjectsCommand projectsToProjectsCommand;
//    private final ProjectsCommandToProjects projectsCommandToProjects;
//
//    @Mock
//    StudentProfileRepository studentProfileRepository;
//
//
//    ProjectsService projectsService;
//
//    public ProjectsServiceImplTest(){
//        this.projectsCommandToProjects= new ProjectsCommandToProjects();
//        this.projectsToProjectsCommand= new ProjectsToProjectsCommand();
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//
//        projectsService = new ProjectsServiceImpl(projectsToProjectsCommand,
//                studentProfileRepository, projectsCommandToProjects);
//    }
//
//    @Test
//    public void findByStudentId() throws Exception {
//    }
//
//    @Test
//    public void findByStudentIdAndProjectId() throws Exception {
//        //given
//        StudentProfile studentProfile = new StudentProfile();
//        studentProfile.setId(1L);
//
//        Projects project1 = new Projects();
//        project1.setId(1L);
//
//        Projects project2 = new Projects();
//        project2.setId(1L);
//
//        Projects project3 = new Projects();
//        project3.setId(3L);
//
//        studentProfile.addProjects(project1);
//        studentProfile.addProjects(project2);
//        studentProfile.addProjects(project3);
//        Optional<StudentProfile> studentProfileOptional = Optional.of(studentProfile);
//
//        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);
//
//        //then
//        ProjectsCommand projectsCommand = projectsService.findByStudentIdAndProjectId(1L, 3L);
//
//        //when
//        assertEquals(Long.valueOf(3L), projectsCommand.getId());
//        assertEquals(Long.valueOf(1L), projectsCommand.getStudentProfileId());
//        verify(studentProfileRepository, times(1)).findById(anyLong());
//    }
//
//
//    @Test
//    public void testSaveRecipeCommand() throws Exception {
//        //given
//        ProjectsCommand command = new ProjectsCommand();
//        command.setId(3L);
//        command.setStudentProfileId(2L);
//
//        Optional<StudentProfile> studentProfileOptional = Optional.of(new StudentProfile());
//
//        StudentProfile savedStudentProfile = new StudentProfile();
//        savedStudentProfile.addProjects(new Projects());
//        savedStudentProfile.getProjects().iterator().next().setId(3L);
//
//        when(studentProfileRepository.findById(anyLong())).thenReturn(studentProfileOptional);
//        when(studentProfileRepository.save(any())).thenReturn(savedStudentProfile);
//
//        //when
//        ProjectsCommand savedCommand = projectsService.saveProjectCommand(command);
//
//        //then
//        assertEquals(Long.valueOf(3L), savedCommand.getId());
//        verify(studentProfileRepository, times(1)).findById(anyLong());
//        verify(studentProfileRepository, times(1)).save(any(StudentProfile.class));
//
//    }
//}