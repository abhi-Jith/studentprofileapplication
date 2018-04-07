package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.SkillSetCommand;
import abhi.springframework.studentprofileapplication.converters.SkillSetToSkillSetCommand;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class SkillSetServiceImpl implements SkillSetService {
    private final SkillSetToSkillSetCommand skillSetToSkillSetCommand;
    private final StudentProfileRepository studentProfileRepository;

    public SkillSetServiceImpl(SkillSetToSkillSetCommand skillSetToSkillSetCommand, StudentProfileRepository studentProfileRepository) {
        this.skillSetToSkillSetCommand = skillSetToSkillSetCommand;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public SkillSetCommand findByStudentIdAndSkillSet(Long studentId, Long skillId) {
        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        if (!studentProfileOptional.isPresent()) {
            //todo impl error handling
            log.error("student id not found. Id:" +studentId);
        }
        StudentProfile studentProfile = studentProfileOptional.get();

        Optional<SkillSetCommand> skillSetCommand= studentProfile.getSkillSets().stream()
                .filter(skill -> skill.getId().equals(skillId))
                .map(skill -> skillSetToSkillSetCommand.convert(skill)).findFirst();

        if (!skillSetCommand.isPresent()){
            //todo error handling
            log.error("Skill Set id not present:" +skillId);
        }
        return skillSetCommand.get();
    }
}