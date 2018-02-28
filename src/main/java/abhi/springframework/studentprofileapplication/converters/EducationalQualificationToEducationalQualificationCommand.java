package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.EducationalQualificationCommand;
import abhi.springframework.studentprofileapplication.domain.EducationalQualification;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EducationalQualificationToEducationalQualificationCommand implements Converter<EducationalQualification,EducationalQualificationCommand> {
    @Synchronized
    @Nullable
    @Override
    public EducationalQualificationCommand convert(EducationalQualification source) {
        if (source == null){
            return null;
        }
        final EducationalQualificationCommand educationalQualificationCommand = new EducationalQualificationCommand();
        educationalQualificationCommand.setId(source.getId());
        educationalQualificationCommand.setInstitutionName(source.getInstitutionName());
        educationalQualificationCommand.setQualification(source.getQualification());
        educationalQualificationCommand.setYear(source.getYear());
        return educationalQualificationCommand;
    }
}
