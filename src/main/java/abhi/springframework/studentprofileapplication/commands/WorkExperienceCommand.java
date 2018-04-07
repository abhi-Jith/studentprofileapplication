package abhi.springframework.studentprofileapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class WorkExperienceCommand {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String companyName;
    @NotBlank
    @Size(min=3, max = 100)
    private String positionName;
    @NotBlank
    @Size(min = 3, max = 100)
    private String companyAddress;
    @Min(5)
    private Integer referenceNumber;
    @NotBlank
    @Size(max = 900)
    private String workExperienceDescription;
    private UnitOfDurationCommand unitOfDuration;
    private Long studentProfileId;
}
