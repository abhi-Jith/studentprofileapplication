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
    public SkillSetCommand convert(SkillSet skillSets) {
        if (skillSets == null){
            return null;
        }
        final SkillSetCommand skillSetCommand = new SkillSetCommand();
        skillSetCommand.setDescription(skillSets.getDescription());
        skillSetCommand.setId(skillSets.getId());
//        if (skillSets.getStudentProfiles()!= null) {
//            skillSetCommand.setStudentProfileId(skillSets.getStudentProfiles());
//        }
        skillSetCommand.setSkillName(skillSets.getSkillName());
        return skillSetCommand;
    }
}
