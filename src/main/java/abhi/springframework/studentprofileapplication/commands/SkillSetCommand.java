package abhi.springframework.studentprofileapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SkillSetCommand {
    private Long id;
    private String skillName;
    private String description;
    private Long studentProfileId;
}
