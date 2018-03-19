package abhi.springframework.studentprofileapplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
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

