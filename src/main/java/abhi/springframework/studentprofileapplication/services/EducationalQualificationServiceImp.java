package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.EducationalQualificationCommand;
import abhi.springframework.studentprofileapplication.converters.EducationalQualificationCommandToEducationalQualification;
import abhi.springframework.studentprofileapplication.converters.EducationalQualificationToEducationalQualificationCommand;
import abhi.springframework.studentprofileapplication.domain.EducationalQualification;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class EducationalQualificationServiceImp implements EducationalQualificationService {
    private final EducationalQualificationToEducationalQualificationCommand educationalQualificationToEducationalQualificationCommand;
    private final StudentProfileRepository studentProfileRepository;
    private final EducationalQualificationCommandToEducationalQualification educationalQualificationCommandToEducationalQualification;


    public EducationalQualificationServiceImp(EducationalQualificationToEducationalQualificationCommand educationalQualificationToEducationalQualificationCommand,
                                              StudentProfileRepository studentProfileRepository, EducationalQualificationCommandToEducationalQualification educationalQualificationCommandToEducationalQualification) {
        this.educationalQualificationToEducationalQualificationCommand = educationalQualificationToEducationalQualificationCommand;
        this.studentProfileRepository = studentProfileRepository;
        this.educationalQualificationCommandToEducationalQualification = educationalQualificationCommandToEducationalQualification;
    }

    @Override
    public EducationalQualificationCommand findByStudentIdAndEducationalId(Long studentId, Long educationalId) {

        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        if (!studentProfileOptional.isPresent()) {
            //todo impl error handling
            log.error("student id not found. Id:" + studentId);
        }
        StudentProfile studentProfile = studentProfileOptional.get();

        Optional<EducationalQualificationCommand> educationalQualificationCommandOptional = studentProfile.getEducationalQualifications().stream()
                .filter(edu -> edu.getId().equals(educationalId))
                .map(edu -> educationalQualificationToEducationalQualificationCommand.convert(edu)).findFirst();

        if (!educationalQualificationCommandOptional.isPresent()) {
            //todo error handling
            log.error("educationallQualification id not present:" + educationalId);
        }
        return educationalQualificationCommandOptional.get();
    }

    @Override
    @Transactional
    public EducationalQualificationCommand saveEducationalQualificationCommand(EducationalQualificationCommand command,Long studentId) {

        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        if (!studentProfileOptional.isPresent()) {
            //todo impl error handling
            log.error("student id not found. Id:" + command.getStudentProfileId());
            return new EducationalQualificationCommand();
        } else {

            StudentProfile studentProfile = studentProfileOptional.get();

            Optional<EducationalQualification> educationalQualificationOptional = studentProfile
                    .getEducationalQualifications()
                    .stream().filter(educational -> educational.getId().equals(command.getId())).findFirst();


            if (educationalQualificationOptional.isPresent()) {
                EducationalQualification educationalQualificationFound = educationalQualificationOptional.get();
                educationalQualificationFound.setInstitutionName(command.getInstitutionName());
                educationalQualificationFound.setQualification(command.getQualification());
                educationalQualificationFound.setYear(command.getYear());
            } else {
                //studentProfile.addEducationalQualification(educationalQualificationCommandToEducationalQualification.convert(command));
                EducationalQualification educationalQualification = educationalQualificationCommandToEducationalQualification.convert(command);
                educationalQualification.setStudentProfile(studentProfile);
                studentProfile.addEducationalQualification(educationalQualification);
            }

            StudentProfile savedStudentProfile = studentProfileRepository.save(studentProfile);

            Optional<EducationalQualification> savedEducationalQualificationOptional= savedStudentProfile.getEducationalQualifications().stream()
                    .filter(studentEducational -> studentEducational.getId().equals(command.getId())).findFirst();

            if (!savedEducationalQualificationOptional.isPresent()) {
                savedEducationalQualificationOptional = savedStudentProfile.getEducationalQualifications().stream()
                        .filter(studentEducational -> studentEducational.getInstitutionName().equals(command.getInstitutionName()))
                        .filter(studentEducational -> studentEducational.getQualification().equals(command.getQualification()))
                        .filter(studentEducational -> studentEducational.getYear().equals(command.getYear())).findFirst();
            }
            return educationalQualificationToEducationalQualificationCommand.convert(savedEducationalQualificationOptional.get());
        }

    }

    @Override
    public void deleteById(Long studentId, Long idToDelete) {
        log.debug("Deleting educationalQualification: " + studentId + ":" + idToDelete);

        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        if(studentProfileOptional.isPresent()){
            StudentProfile studentProfile = studentProfileOptional.get();
            log.debug("found student profile");

            Optional<EducationalQualification> educationalQualificationOptional = studentProfile
                    .getEducationalQualifications()
                    .stream()
                    .filter(edu -> edu.getId().equals(idToDelete))
                    .findFirst();

            if(educationalQualificationOptional.isPresent()){
                log.debug("found educational qualification");
                EducationalQualification educationalQualificationToDelete = educationalQualificationOptional.get();
                educationalQualificationToDelete.setStudentProfile(null);
                studentProfile.getEducationalQualifications().remove(educationalQualificationOptional.get());
                studentProfileRepository.save(studentProfile);
            }
        } else {
            log.debug("Student Id Not found. Id:" + studentId);
        }
    }
}


