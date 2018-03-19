package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.WorkExperienceCommand;
import abhi.springframework.studentprofileapplication.domain.WorkExperience;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class WorkExperienceToWorkExperienceCommand implements Converter<WorkExperience,WorkExperienceCommand> {
    private final UnitOfDurationToUnitOfDurationCommand unitOfDurationToUnitOfDurationCommand;

    public WorkExperienceToWorkExperienceCommand(UnitOfDurationToUnitOfDurationCommand unitOfDurationToUnitOfDurationCommand) {
        this.unitOfDurationToUnitOfDurationCommand = unitOfDurationToUnitOfDurationCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public WorkExperienceCommand convert(WorkExperience source) {
        if (source == null){
            return null;
        }
        WorkExperienceCommand workExperienceCommand= new WorkExperienceCommand();
        workExperienceCommand.setId(source.getId());
        if (source.getStudentProfile()!= null) {
            workExperienceCommand.setStudentProfileId(source.getStudentProfile().getId());
        }
        workExperienceCommand.setCompanyAddress(source.getCompanyAddress());
        workExperienceCommand.setCompanyName(source.getCompanyName());
        workExperienceCommand.setPositionName(source.getPositionName());
        workExperienceCommand.setReferenceNumber(source.getReferenceNumber());

        workExperienceCommand.setWorkExperienceDescription(source.getWorkExperienceDescription());
        workExperienceCommand.setUnitOfDuration(unitOfDurationToUnitOfDurationCommand.convert(source.getUnitOfDuration()));
        return workExperienceCommand;
    }
}
