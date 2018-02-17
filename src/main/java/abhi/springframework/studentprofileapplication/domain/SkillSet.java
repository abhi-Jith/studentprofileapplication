package abhi.springframework.studentprofileapplication.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
@Data
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
