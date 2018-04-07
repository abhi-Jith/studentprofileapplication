package abhi.springframework.studentprofileapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectsCommand {
    private Long id;
    private String projectDescription;
    private String projectTitle;
    private String technologiesUsed;
    private Long studentProfileId;
}
