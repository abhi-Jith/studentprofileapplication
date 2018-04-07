package abhi.springframework.studentprofileapplication.services;

import abhi.springframework.studentprofileapplication.commands.UnitOfDurationCommand;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface UnitOfDurationService {
    Set<UnitOfDurationCommand> listAllUod();
}
