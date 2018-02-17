package abhi.springframework.studentprofileapplication.repositories;

import abhi.springframework.studentprofileapplication.domain.SkillSet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SkillSetRepository extends CrudRepository<SkillSet,Long> {
    Optional<SkillSet>findBySkillName(String skillName);
}
