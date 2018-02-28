package abhi.springframework.studentprofileapplication.converters;

import abhi.springframework.studentprofileapplication.commands.AboutmeCommand;
import abhi.springframework.studentprofileapplication.domain.Aboutme;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AboutmeCommandToAboutme implements Converter<AboutmeCommand,Aboutme> {

    @Synchronized
    @Nullable
    @Override
    public Aboutme convert(AboutmeCommand aboutmeCommand) {
        if (aboutmeCommand == null){
        return null;
    }
    final Aboutme aboutme =new Aboutme();
    aboutme.setId(aboutmeCommand.getId());
    aboutme.setAboutMe(aboutmeCommand.getAboutMe());
    return aboutme;
    }
}
