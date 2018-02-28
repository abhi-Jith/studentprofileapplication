package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.EducationalQualificationCommand;
import abhi.springframework.studentprofileapplication.domain.EducationalQualification;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EducationalQualificationCommandToEducationalQualification implements Converter<EducationalQualificationCommand,EducationalQualification> {
    @Synchronized
    @Nullable
    @Override
    public EducationalQualification convert(EducationalQualificationCommand source) {
        if (source == null) {
            return null;
        }
        final EducationalQualification educationalQualification = new EducationalQualification();
        educationalQualification.setQualification(source.getQualification());
        educationalQualification.setId(source.getId());
        educationalQualification.setInstitutionName(source.getInstitutionName());
        educationalQualification.setYear(source.getYear());
        return educationalQualification;
    }
}
