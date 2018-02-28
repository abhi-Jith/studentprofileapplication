package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.AboutmeCommand;
import abhi.springframework.studentprofileapplication.domain.Aboutme;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AboutmeToAboutmeCommand implements Converter<Aboutme,AboutmeCommand> {
    @Synchronized
    @Nullable
    @Override
    public AboutmeCommand convert(Aboutme aboutme) {
        if (aboutme == null){
            return null;
        }
        final AboutmeCommand aboutmeCommand =new AboutmeCommand();
        aboutmeCommand.setId(aboutme.getId());
        aboutmeCommand.setAboutMe(aboutme.getAboutMe());
        return aboutmeCommand;
    }

}
