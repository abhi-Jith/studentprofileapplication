package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.UnitOfDurationCommand;
import abhi.springframework.studentprofileapplication.converters.UnitOfDurationToUnitOfDurationCommand;
import abhi.springframework.studentprofileapplication.domain.UnitOfDuration;
import abhi.springframework.studentprofileapplication.repositories.UnitOfDurationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfDurationServiceImplTest {
    UnitOfDurationToUnitOfDurationCommand unitOfDurationToUnitOfDurationCommand = new UnitOfDurationToUnitOfDurationCommand();
    UnitOfDurationService  service;

    @Mock
    UnitOfDurationRepository unitOfDurationRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfDurationServiceImpl(unitOfDurationRepository, unitOfDurationToUnitOfDurationCommand);
    }

    @Test
    public void listAllUod() throws Exception {
        //given
        Set<UnitOfDuration> unitOfDurations = new HashSet<>();
        UnitOfDuration uod1 = new UnitOfDuration();
        uod1.setId(1L);
        unitOfDurations.add(uod1);

        UnitOfDuration uod2 = new UnitOfDuration();
        uod2.setId(1L);
        unitOfDurations.add(uod2);

        when(unitOfDurationRepository.findAll()).thenReturn(unitOfDurations);

        //when
        Set<UnitOfDurationCommand> commands = service.listAllUod();

        //then
        assertEquals(2, commands.size());
        verify(unitOfDurationRepository, times(1)).findAll();
    }
}