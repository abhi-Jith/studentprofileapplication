package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.EducationalQualificationCommand;
import org.springframework.stereotype.Service;

@Service
public interface EducationalQualificationService {
    EducationalQualificationCommand findByStudentIdAndEducationalId(Long studentId, Long educationalId);
    EducationalQualificationCommand saveEducationalQualificationCommand(EducationalQualificationCommand command, Long studentId);
    void deleteById(Long studentId, Long idToDelete);
}
