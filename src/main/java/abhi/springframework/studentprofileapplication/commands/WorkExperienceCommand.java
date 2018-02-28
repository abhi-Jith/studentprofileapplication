package abhi.springframework.studentprofileapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkExperienceCommand {
    private Long id;
    private String companyName;
    private String positionName;
    private String companyAddress;
    private Integer referenceNumber;
    private String workExperienceDescription;
    private UnitOfDurationCommand unitOfDuration;
}
