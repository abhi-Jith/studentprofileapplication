package abhi.springframework.studentprofileapplication.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SkillSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;
    private String description;

    @ManyToMany(mappedBy ="skillSets" )
    private Set<StudentProfile> studentProfiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StudentProfile> getStudentProfiles() {
        return studentProfiles;
    }

    public void setStudentProfiles(Set<StudentProfile> studentProfiles) {
        this.studentProfiles = studentProfiles;
    }
}
