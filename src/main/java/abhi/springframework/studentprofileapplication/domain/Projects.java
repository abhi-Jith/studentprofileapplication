package abhi.springframework.studentprofileapplication.domain;

import javax.persistence.*;

@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String projectDescription;

    private String projectTitle;
    private String technologiesUsed;

    @ManyToOne
    private StudentProfile studentProfile;

    public Projects() {
    }

    public Projects(String projectDescription, String projectTitle, String technologiesUsed) {
        this.projectDescription = projectDescription;
        this.projectTitle = projectTitle;
        this.technologiesUsed = technologiesUsed;
    }

    public Projects(String projectDescription, String projectTitle, String technologiesUsed, StudentProfile studentProfile) {
        this.projectDescription = projectDescription;
        this.projectTitle = projectTitle;
        this.technologiesUsed = technologiesUsed;
        this.studentProfile = studentProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getTechnologiesUsed() {
        return technologiesUsed;
    }

    public void setTechnologiesUsed(String technologiesUsed) {
        this.technologiesUsed = technologiesUsed;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
}

