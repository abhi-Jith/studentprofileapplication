package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.WorkExperienceCommand;
import org.springframework.stereotype.Service;

@Service
public interface WorkExperienceService {
    WorkExperienceCommand findByStudentIdAndWorkExperienceId(Long studentId, Long WorkId);
    WorkExperienceCommand saveWorkExperience(WorkExperienceCommand command, Long studentId);
    void deleteByIdWorkExperience(Long studentId, Long idToDelete);
}