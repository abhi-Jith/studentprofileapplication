package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.EducationalQualificationCommand;
import abhi.springframework.studentprofileapplication.domain.EducationalQualification;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
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

        educationalQualification.setId(source.getId());

        if(source.getStudentProfileId() != null){
            StudentProfile studentProfile = new StudentProfile();
            studentProfile.setId(source.getStudentProfileId());
            educationalQualification.setStudentProfile(studentProfile);
            studentProfile.addEducationalQualification(educationalQualification);
        }

        educationalQualification.setInstitutionName(source.getInstitutionName());
        educationalQualification.setQualification(source.getQualification());
        educationalQualification.setYear(source.getYear());
        return educationalQualification;
    }
}
