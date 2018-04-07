package abhi.springframework.studentprofileapplication.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String address;
    private String emailId;
    private Integer ssn;
    private String mobileNumber;

    @Lob
    private Byte[] image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentProfile")
    private Set<Projects> projects = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentProfile")
    private Set<WorkExperience> workExperiences = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentProfile")
    private Set<EducationalQualification> educationalQualifications = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Aboutme aboutme;

    @Enumerated(value = EnumType.STRING)
    private CodingLevel codingLevel;

    @ManyToMany
    @JoinTable(name = "studentProfile_skillSet",
            joinColumns = @JoinColumn(name = "studentProfile_id"),
            inverseJoinColumns = @JoinColumn(name = "skillSet_id"))
    private Set<SkillSet> skillSets = new HashSet<>();


    public void setAboutme(Aboutme aboutme) {
        if (aboutme != null) {
            this.aboutme = aboutme;
            aboutme.setStudentProfile(this);
        }
    }

    public StudentProfile addEducationalQualification(EducationalQualification educationalQualification) {
        educationalQualification.setStudentProfile(this);
        this.educationalQualifications.add(educationalQualification);
        return this;
    }

    public StudentProfile addProjects(Projects project) {
        project.setStudentProfile(this);
        this.projects.add(project);
        return this;
    }


    public StudentProfile addWorkExperience(WorkExperience workExperience) {
        workExperience.setStudentProfile(this);
        this.workExperiences.add(workExperience);
        return this;
    }

}
