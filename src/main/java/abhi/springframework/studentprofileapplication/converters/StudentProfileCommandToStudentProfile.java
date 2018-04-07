package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StudentProfileCommandToStudentProfile  implements Converter<StudentProfileCommand,StudentProfile> {
    private final AboutmeCommandToAboutme aboutmeConverter;
    private final EducationalQualificationCommandToEducationalQualification educationalQualificationConverter;
    private final ProjectsCommandToProjects projectsConverter;
    private final SkillSetCommandToSkillSet skillSetConverter;
    private final WorkExperienceCommandToWorkExperience workExperienceConverter;

    public StudentProfileCommandToStudentProfile(AboutmeCommandToAboutme aboutmeConverter, EducationalQualificationCommandToEducationalQualification educationalQualificationConverter, ProjectsCommandToProjects projectsConverter, SkillSetCommandToSkillSet skillSetConverter, WorkExperienceCommandToWorkExperience workExperienceConverter) {
        this.aboutmeConverter = aboutmeConverter;
        this.educationalQualificationConverter = educationalQualificationConverter;
        this.projectsConverter = projectsConverter;
        this.skillSetConverter = skillSetConverter;
        this.workExperienceConverter = workExperienceConverter;
    }
    @Synchronized
    @Nullable
    @Override
    public StudentProfile convert(StudentProfileCommand source) {
        if (source == null ){
            return null;
        }
        final StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(source.getId());
        studentProfile.setCodingLevel(source.getCodingLevel());

        studentProfile.setAboutme(aboutmeConverter.convert(source.getAboutme()));
        studentProfile.setSsn(source.getSsn());
        studentProfile.setEmailId(source.getEmailId());
        studentProfile.setDob(source.getDob());
        studentProfile.setGender(source.getGender());
        studentProfile.setAddress(source.getAddress());
        studentProfile.setFirstName(source.getFirstName());
        studentProfile.setLastName(source.getLastName());
        studentProfile.setMobileNumber(source.getMobileNumber());

        if (source.getEducationalQualifications() != null && source.getEducationalQualifications().size() > 0){
            source.getEducationalQualifications()
                    .forEach( educational -> studentProfile.getEducationalQualifications()
                            .add(educationalQualificationConverter.convert(educational)));
        }

        if (source.getProjects() != null && source.getProjects().size() > 0){
            source.getProjects()
                    .forEach( project -> studentProfile.getProjects()
                            .add(projectsConverter.convert(project)));
        }
        if (source.getWorkExperiences() != null && source.getWorkExperiences().size() > 0){
            source.getWorkExperiences()
                    .forEach( workExperience -> studentProfile.getWorkExperiences()
                            .add(workExperienceConverter.convert(workExperience)));
        }
        if (source.getSkillSets() != null && source.getSkillSets().size() > 0){
            source.getSkillSets()
                    .forEach( skillset -> studentProfile.getSkillSets()
                            .add(skillSetConverter.convert(skillset)));
        }
        return studentProfile;
    }
}
