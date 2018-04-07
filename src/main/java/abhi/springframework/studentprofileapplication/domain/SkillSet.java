package abhi.springframework.studentprofileapplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@EqualsAndHashCode(exclude = "studentProfiles")
@Entity
public class SkillSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;
    private String description;

    @ManyToMany(mappedBy ="skillSets" )
    private Set<StudentProfile> studentProfiles;
}
