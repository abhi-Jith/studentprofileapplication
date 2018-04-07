package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface StudentProfileService {

    Set<StudentProfile> getStudentProfile();
    StudentProfile findById(Long l);
    StudentProfileCommand saveStudentProfileCommand( StudentProfileCommand studentProfileCommand);
    StudentProfileCommand findCommandById(Long l);
    void deleteById(Long l);

}
