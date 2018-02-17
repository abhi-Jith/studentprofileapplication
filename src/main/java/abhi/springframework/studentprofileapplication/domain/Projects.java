package abhi.springframework.studentprofileapplication.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(exclude = "studentProfile")
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
}

