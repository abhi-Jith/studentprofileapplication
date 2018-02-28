package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.SkillSetCommand;
import abhi.springframework.studentprofileapplication.domain.SkillSet;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SkillSetToSkillSetCommand implements Converter<SkillSet,SkillSetCommand> {
    @Synchronized
    @Nullable
    @Override
    public SkillSetCommand convert(SkillSet skillSet) {
        if (skillSet == null){
            return null;
        }
        final SkillSetCommand skillSetCommand = new SkillSetCommand();
        skillSetCommand.setDescription(skillSet.getDescription());
        skillSetCommand.setId(skillSet.getId());
        skillSetCommand.setSkillName(skillSet.getSkillName());
        return skillSetCommand;
    }
}
