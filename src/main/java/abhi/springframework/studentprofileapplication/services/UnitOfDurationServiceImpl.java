package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.UnitOfDurationCommand;
import abhi.springframework.studentprofileapplication.converters.UnitOfDurationToUnitOfDurationCommand;
import abhi.springframework.studentprofileapplication.repositories.UnitOfDurationRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfDurationServiceImpl implements UnitOfDurationService {
    private  final UnitOfDurationRepository unitOfDurationRepository;
    private final UnitOfDurationToUnitOfDurationCommand unitOfDurationToUnitOfDurationCommand;

    public UnitOfDurationServiceImpl(UnitOfDurationRepository unitOfDurationRepository,
                                     UnitOfDurationToUnitOfDurationCommand unitOfDurationToUnitOfDurationCommand) {
        this.unitOfDurationRepository = unitOfDurationRepository;
        this.unitOfDurationToUnitOfDurationCommand = unitOfDurationToUnitOfDurationCommand;
    }

    @Override
    public Set<UnitOfDurationCommand> listAllUod() {
            return StreamSupport.stream(unitOfDurationRepository.findAll().spliterator(),false)
                    .map(unitOfDurationToUnitOfDurationCommand::convert).collect(Collectors.toSet());
    }
}
