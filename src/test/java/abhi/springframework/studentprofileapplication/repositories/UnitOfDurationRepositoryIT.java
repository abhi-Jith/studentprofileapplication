package abhi.springframework.studentprofileapplication.repositories;

import abhi.springframework.studentprofileapplication.domain.UnitOfDuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfDurationRepositoryIT {
    @Autowired
    UnitOfDurationRepository unitOfDurationRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findByUodMonth() throws Exception {
        Optional<UnitOfDuration> unitOfDurationOptional = unitOfDurationRepository.findByUod("Months");
        assertEquals("Months",unitOfDurationOptional.get().getUod());
    }

    @Test
    public void findByUod() throws Exception {
        Optional<UnitOfDuration> unitOfDurationOptional = unitOfDurationRepository.findByUod("Years");
        assertEquals("Years",unitOfDurationOptional.get().getUod());
    }

}