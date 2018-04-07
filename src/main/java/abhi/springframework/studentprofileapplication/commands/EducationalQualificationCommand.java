package abhi.springframework.studentprofileapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EducationalQualificationCommand {
    private Long id;
    private String institutionName;
    private String qualification;
    private String year;
    private Long studentProfileId;
}
