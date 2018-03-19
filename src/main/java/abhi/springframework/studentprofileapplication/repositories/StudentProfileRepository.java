package abhi.springframework.studentprofileapplication.repositories;

import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentProfileRepository extends CrudRepository<StudentProfile,Long> {
    Optional<StudentProfile> findById(Long id);
}
