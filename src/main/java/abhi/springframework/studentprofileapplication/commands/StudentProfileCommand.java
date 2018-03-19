package abhi.springframework.studentprofileapplication.commands;

import abhi.springframework.studentprofileapplication.domain.CodingLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class StudentProfileCommand {
    private Long id;
    @NotBlank
    @Size( min = 1, max = 20)
    private String firstName;

    @NotBlank
    @Size( min =1, max = 20)
    private String lastName;

    @NotBlank
    private String dob;

    @NotBlank
    @Size(max = 6)
    private String gender;

    @NotBlank
    @Size(max = 100)
    private String address;

    @Email
    private String emailId;

    @Min(10)
    private Integer ssn;

    @NotBlank
    @Size(max = 10)
    private String mobileNumber;
    private Byte[] image;
    private Set<ProjectsCommand> projects = new HashSet<>();
    private Set<WorkExperienceCommand> workExperiences = new HashSet<>();
    private Set<EducationalQualificationCommand> educationalQualifications = new HashSet<>();
    private Set<SkillSetCommand> skillSets = new HashSet<>();
    private AboutmeCommand aboutme;
    private CodingLevel codingLevel;

}
