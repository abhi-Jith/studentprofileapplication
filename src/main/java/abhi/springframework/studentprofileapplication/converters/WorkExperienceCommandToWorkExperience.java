package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.WorkExperienceCommand;
import abhi.springframework.studentprofileapplication.domain.WorkExperience;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class WorkExperienceCommandToWorkExperience implements Converter<WorkExperienceCommand,WorkExperience> {
    private final UnitOfDurationCommandToUnitOfDuration unitOfDurationCommandToUnitOfDuration;

    public WorkExperienceCommandToWorkExperience(UnitOfDurationCommandToUnitOfDuration unitOfDurationCommandToUnitOfDuration) {
        this.unitOfDurationCommandToUnitOfDuration = unitOfDurationCommandToUnitOfDuration;
    }

    @Synchronized
    @Nullable
    @Override
    public WorkExperience convert(WorkExperienceCommand source) {
        if (source == null){
            return null;
        }
        final WorkExperience workExperience= new WorkExperience();
        workExperience.setId(source.getId());
        workExperience.setCompanyAddress(source.getCompanyAddress());
        workExperience.setCompanyName(source.getCompanyName());
        workExperience.setPositionName(source.getPositionName());
        workExperience.setReferenceNumber(source.getReferenceNumber());
        workExperience.setWorkExperienceDescription(source.getWorkExperienceDescription());
        workExperience.setUnitOfDuration(unitOfDurationCommandToUnitOfDuration.convert(source.getUnitOfDuration()));
        return workExperience;
    }
}
