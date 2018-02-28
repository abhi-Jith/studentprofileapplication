package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.UnitOfDurationCommand;
import abhi.springframework.studentprofileapplication.domain.UnitOfDuration;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfDurationToUnitOfDurationCommand implements Converter<UnitOfDuration,UnitOfDurationCommand> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfDurationCommand convert(UnitOfDuration unitOfDuration) {
        if (unitOfDuration == null) {
            return null;
        }
        final UnitOfDurationCommand unitOfDurationCommand = new UnitOfDurationCommand();
        unitOfDurationCommand.setId(unitOfDuration.getId());
        unitOfDurationCommand.setUod(unitOfDuration.getUod());
        return unitOfDurationCommand;
    }
}
