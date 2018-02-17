package abhi.springframework.studentprofileapplication.repositories;

import abhi.springframework.studentprofileapplication.domain.StudentProfile;
import org.springframework.data.repository.CrudRepository;

public interface StudentProfileRepository extends CrudRepository<StudentProfile,Long> {
}
