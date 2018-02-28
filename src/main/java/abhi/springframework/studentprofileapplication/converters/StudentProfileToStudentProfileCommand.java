package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.domain.*;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StudentProfileToStudentProfileCommand implements Converter<StudentProfile,StudentProfileCommand> {
    private final AboutmeToAboutmeCommand aboutmeConverter;
    private final EducationalQualificationToEducationalQualificationCommand educationalQualificationConverter;
    private final ProjectsToProjectsCommand projectsConverter;
    private final SkillSetToSkillSetCommand skillSetConverter;
    private final WorkExperienceToWorkExperienceCommand workExperienceConverter;

    public StudentProfileToStudentProfileCommand(AboutmeToAboutmeCommand aboutmeConverter,
                                                 EducationalQualificationToEducationalQualificationCommand educationalQualificationConverter,
                                                 ProjectsToProjectsCommand projectsConverter, SkillSetToSkillSetCommand skillSetConverter,
                                                 WorkExperienceToWorkExperienceCommand workExperienceConverter) {
        this.aboutmeConverter = aboutmeConverter;
        this.educationalQualificationConverter = educationalQualificationConverter;
        this.projectsConverter = projectsConverter;
        this.skillSetConverter = skillSetConverter;
        this.workExperienceConverter = workExperienceConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public StudentProfileCommand convert(StudentProfile source) {
        if (source == null) {
            return null;
        }
        final StudentProfileCommand studentProfileCommand = new StudentProfileCommand();
        studentProfileCommand.setId(source.getId());
        studentProfileCommand.setCodingLevel(source.getCodingLevel());

        studentProfileCommand.setAboutme(aboutmeConverter.convert(source.getAboutme()));
        studentProfileCommand.setSsn(source.getSsn());
        studentProfileCommand.setEmailId(source.getEmailId());
        studentProfileCommand.setDob(source.getDob());
        studentProfileCommand.setGender(source.getGender());
        studentProfileCommand.setAddress(source.getAddress());
        studentProfileCommand.setFirstName(source.getFirstName());
        studentProfileCommand.setLastName(source.getLastName());
        studentProfileCommand.setMobileNumber(source.getMobileNumber());

        if (source.getEducationalQualifications() != null && source.getEducationalQualifications().size() > 0) {
            source.getEducationalQualifications()
                    .forEach((EducationalQualification educational) -> studentProfileCommand.getEducationalQualifications()
                            .add(educationalQualificationConverter.convert(educational)));
        }

        if (source.getProjects() != null && source.getProjects().size() > 0) {
            source.getProjects()
                    .forEach((Projects project) -> studentProfileCommand.getProjects()
                            .add(projectsConverter.convert(project)));
        }
        if (source.getWorkExperiences() != null && source.getWorkExperiences().size() > 0) {
            source.getWorkExperiences()
                    .forEach((WorkExperience workExperience) -> studentProfileCommand.getWorkExperiences()
                            .add(workExperienceConverter.convert(workExperience)));
        }
        if (source.getSkillSets() != null && source.getSkillSets().size() > 0) {
            source.getSkillSets()
                    .forEach((SkillSet skillset) -> studentProfileCommand.getSkillSets()
                            .add(skillSetConverter.convert(skillset)));
        }
        return studentProfileCommand;
    }
}
