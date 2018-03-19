package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.WorkExperienceCommand;
import abhi.springframework.studentprofileapplication.converters.WorkExperienceCommandToWorkExperience;
import abhi.springframework.studentprofileapplication.converters.WorkExperienceToWorkExperienceCommand;
import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import abhi.springframework.studentprofileapplication.domain.WorkExperience;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import abhi.springframework.studentprofileapplication.repositories.UnitOfDurationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {
    private final WorkExperienceToWorkExperienceCommand workExperienceToWorkExperienceCommand;
    private final StudentProfileRepository studentProfileRepository;
    private final WorkExperienceCommandToWorkExperience workExperienceCommandToWorkExperience;
    private final UnitOfDurationRepository unitOfDurationRepository;

    public WorkExperienceServiceImpl(WorkExperienceToWorkExperienceCommand workExperienceToWorkExperienceCommand,
                                     StudentProfileRepository studentProfileRepository, WorkExperienceCommandToWorkExperience workExperienceCommandToWorkExperience, UnitOfDurationRepository unitOfDurationRepository) {
        this.workExperienceToWorkExperienceCommand = workExperienceToWorkExperienceCommand;
        this.studentProfileRepository = studentProfileRepository;
        this.workExperienceCommandToWorkExperience = workExperienceCommandToWorkExperience;
        this.unitOfDurationRepository = unitOfDurationRepository;
    }

    @Override
    public WorkExperienceCommand findByStudentIdAndWorkExperienceId(Long studentId, Long WorkId) {
        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        if (!studentProfileOptional.isPresent()) {
            //todo impl error handling
            log.error("student id not found. Id:" + studentId);
        }
        StudentProfile studentProfile = studentProfileOptional.get();

        Optional<WorkExperienceCommand> workExperienceCommandOptional = studentProfile.getWorkExperiences().stream()
                .filter(work -> work.getId().equals(WorkId))
                .map(work -> workExperienceToWorkExperienceCommand.convert(work)).findFirst();

        if (!workExperienceCommandOptional.isPresent()) {
            //todo error handling
            log.error("Work Experience id not present:" + WorkId);
        }
        return workExperienceCommandOptional.get();
    }

    @Override
    @Transactional
    public WorkExperienceCommand saveWorkExperience(WorkExperienceCommand command, Long studentId) {
        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        if (!studentProfileOptional.isPresent()) {
            log.error("student id not found:" + studentId);
            return new WorkExperienceCommand();
        } else {
            StudentProfile studentProfile = studentProfileOptional.get();

            Optional<WorkExperience> workExperienceOptional = studentProfile.getWorkExperiences().stream()
                    .filter(work -> work.getId().equals(command.getId())).findFirst();

            if (workExperienceOptional.isPresent()) {
                WorkExperience workExperienceFound = workExperienceOptional.get();
                workExperienceFound.setPositionName(command.getPositionName());
                workExperienceFound.setReferenceNumber(command.getReferenceNumber());
                workExperienceFound.setCompanyName(command.getCompanyName());
                workExperienceFound.setCompanyAddress(command.getCompanyAddress());
                workExperienceFound.setWorkExperienceDescription(command.getWorkExperienceDescription());
                workExperienceFound.setUnitOfDuration(unitOfDurationRepository.findById(command.getUnitOfDuration()
                            .getId()).orElseThrow(() -> new RuntimeException("UOD NOT FOUND")));
            } else {
                    WorkExperience workExperience = workExperienceCommandToWorkExperience.convert(command);
                    workExperience.setStudentProfile(studentProfile);
                    studentProfile.addWorkExperience(workExperience);
            }

            StudentProfile savedStudentProfile = studentProfileRepository.save(studentProfile);

            Optional<WorkExperience> savedWorkExperienceOptional = savedStudentProfile.getWorkExperiences().stream()
                    .filter(work -> work.getId().equals(command.getId())).findFirst();

            if (!savedWorkExperienceOptional.isPresent()) {
                savedWorkExperienceOptional = savedStudentProfile.getWorkExperiences().stream()
                        .filter(work -> work.getPositionName().equals(command.getPositionName()))
                        .filter(work -> work.getReferenceNumber().equals(command.getReferenceNumber()))
                        .filter(work -> work.getCompanyName().equals(command.getCompanyName()))
                        .filter(work -> work.getCompanyAddress().equals(command.getCompanyAddress()))
                        .filter(work -> work.getWorkExperienceDescription().equals(command.getWorkExperienceDescription()))
                        .filter(work -> work.getUnitOfDuration().getId().equals(command.getUnitOfDuration().getId())).findFirst();
            }
            return workExperienceToWorkExperienceCommand.convert(savedWorkExperienceOptional.get());

        }
    }

    @Override
    public void deleteByIdWorkExperience(Long studentId, Long idToDelete) {
        log.debug("Deleting WorkExperience: " + studentId + ":" + idToDelete);

        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findById(studentId);

        if (studentProfileOptional.isPresent()) {
            StudentProfile studentProfile = studentProfileOptional.get();
            log.debug("found student profile");

            Optional<WorkExperience> workExperienceOptional = studentProfile
                    .getWorkExperiences()
                    .stream()
                    .filter(work -> work.getId().equals(idToDelete))
                    .findFirst();

            if (workExperienceOptional.isPresent()) {
                log.debug("found workExperience");
                WorkExperience workExperienceToDelete = workExperienceOptional.get();
                workExperienceToDelete.setStudentProfile(null);
                studentProfile.getWorkExperiences().remove(workExperienceOptional.get());
                studentProfileRepository.save(studentProfile);
            }
        } else {
            log.debug("Student Id Not found. Id:" + studentId);
        }
    }
}

