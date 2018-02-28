package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.StudentProfileCommand;
import abhi.springframework.studentprofileapplication.converters.StudentProfileCommandToStudentProfile;
import abhi.springframework.studentprofileapplication.converters.StudentProfileToStudentProfileCommand;
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
    private final StudentProfileCommandToStudentProfile studentProfileCommandToStudentProfile;
    private final StudentProfileToStudentProfileCommand studentProfileToStudentProfileCommand;

    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository, StudentProfileCommandToStudentProfile studentProfileCommandToStudentProfile, StudentProfileToStudentProfileCommand studentProfileToStudentProfileCommand) {
        this.studentProfileRepository = studentProfileRepository;
        this.studentProfileCommandToStudentProfile = studentProfileCommandToStudentProfile;
        this.studentProfileToStudentProfileCommand = studentProfileToStudentProfileCommand;
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

    @Override
    public StudentProfileCommand findCommandById(Long l) {
        return studentProfileToStudentProfileCommand.convert(findById(l));
    }

    @Override
    public StudentProfileCommand saveStudentProfileCommand(StudentProfileCommand studentProfileCommand) {

        StudentProfile detachedStudentProfile = studentProfileCommandToStudentProfile.convert(studentProfileCommand);

        StudentProfile savedStudentProfile = studentProfileRepository.save(detachedStudentProfile);

        log.debug("Saved StudentProfileID: "+ savedStudentProfile.getId());

        return studentProfileToStudentProfileCommand.convert(savedStudentProfile);
    }

    @Override
    public void deleteById(Long l) {
        studentProfileRepository.deleteById(l);
    }
}
