package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Slf4j
@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public Set<StudentProfile> getStudentProfile() {
        log.debug("Getting student profiles");
        Set<StudentProfile> studentProfileSet = new HashSet<>();
        studentProfileRepository.findAll().iterator().forEachRemaining(studentProfileSet::add);

        return studentProfileSet;
    }
}
