package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface StudentProfileService {

    Set<StudentProfile> getStudentProfile();
}
