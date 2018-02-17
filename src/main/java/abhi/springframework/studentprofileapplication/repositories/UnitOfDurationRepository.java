package abhi.springframework.studentprofileapplication.repositories;

import abhi.springframework.studentprofileapplication.domain.UnitOfDuration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfDurationRepository extends CrudRepository<UnitOfDuration,Long> {
    Optional<UnitOfDuration>findByUod(String uod);
}
