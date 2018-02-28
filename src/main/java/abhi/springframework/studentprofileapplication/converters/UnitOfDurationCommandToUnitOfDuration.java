package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.UnitOfDurationCommand;
import abhi.springframework.studentprofileapplication.domain.UnitOfDuration;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfDurationCommandToUnitOfDuration implements Converter<UnitOfDurationCommand,UnitOfDuration> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfDuration convert(UnitOfDurationCommand unitOfDurationCommand) {
        if (unitOfDurationCommand == null) {
            return null;
        }
        final UnitOfDuration unitOfDuration = new UnitOfDuration();
        unitOfDuration.setId(unitOfDurationCommand.getId());
        unitOfDuration.setUod(unitOfDurationCommand.getUod());
        return unitOfDuration;
    }
}
