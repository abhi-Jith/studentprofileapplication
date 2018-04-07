package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.SkillSetCommand;

public interface SkillSetService {
    SkillSetCommand findByStudentIdAndSkillSet(Long studentId, Long skillId);
}
