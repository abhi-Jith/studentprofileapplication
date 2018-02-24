package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
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

    @Override
    public StudentProfile findById(Long l) {
        log.debug("Getting Student profile by id");

        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(l);

        if (!studentProfileOptional.isPresent()){
            throw  new RuntimeException("Student Profile Not Found.");
        }

        return studentProfileOptional.get();

    }

}
