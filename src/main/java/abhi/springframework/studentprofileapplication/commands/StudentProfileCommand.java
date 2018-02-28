package abhi.springframework.studentprofileapplication.commands;

import abhi.springframework.studentprofileapplication.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class StudentProfileCommand {
    private Long id;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String address;
    private String emailId;
    private Integer ssn;
    private String mobileNumber;
    private Set<ProjectsCommand> projects = new HashSet<>();
    private Set<WorkExperienceCommand> workExperiences = new HashSet<>();
    private Set<EducationalQualificationCommand> educationalQualifications = new HashSet<>();
    private Set<SkillSetCommand> skillSets = new HashSet<>();
    private AboutmeCommand aboutme;
    private CodingLevel codingLevel;

}
