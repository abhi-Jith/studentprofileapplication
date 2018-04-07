package abhi.springframework.studentprofileapplication.bootstrap;

import abhi.springframework.studentprofileapplication.domain.SkillSet;
import abhi.springframework.studentprofileapplication.domain.UnitOfDuration;
import abhi.springframework.studentprofileapplication.repositories.SkillSetRepository;
import abhi.springframework.studentprofileapplication.repositories.UnitOfDurationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {
    private final SkillSetRepository skillSetRepository;
    private final UnitOfDurationRepository unitOfDurationRepository;

    public BootStrapMySQL(SkillSetRepository skillSetRepository, UnitOfDurationRepository unitOfDurationRepository) {
        this.skillSetRepository = skillSetRepository;
        this.unitOfDurationRepository = unitOfDurationRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (skillSetRepository.count() == 0L){
            log.debug("Loading SkillSet");
            loadSkillSet();
        }

        if (unitOfDurationRepository.count() == 0L){
            log.debug("Loading UODs");
            loadUod();
        }
    }

    private void loadSkillSet(){
        SkillSet skill1 = new SkillSet();
        skill1.setDescription("Programming");
        skill1.setSkillName("Java J2EE");
        skillSetRepository.save(skill1);

        SkillSet skill2 = new SkillSet();
        skill2.setDescription("Programming");
        skill2.setSkillName("Spring Framework");
        skillSetRepository.save(skill2);

        SkillSet skill3 = new SkillSet();
        skill3.setDescription("Programming");
        skill3.setSkillName("Hibernate");
        skillSetRepository.save(skill3);

        SkillSet skill4 = new SkillSet();
        skill4.setDescription("Programming");
        skill4.setSkillName("Thymeleaf");
        skillSetRepository.save(skill4);

        SkillSet skill5 = new SkillSet();
        skill5.setDescription("Programming");
        skill5.setSkillName("C++");
        skillSetRepository.save(skill5);

        SkillSet skill6 = new SkillSet();
        skill6.setDescription("Programming");
        skill6.setSkillName("C Language");
        skillSetRepository.save(skill6);

        SkillSet skill7 = new SkillSet();
        skill7.setDescription("Web Technologies");
        skill7.setSkillName("Angular JS");
        skillSetRepository.save(skill7);

        SkillSet skill8 = new SkillSet();
        skill8.setDescription("Web Technologies");
        skill8.setSkillName("Node JS");
        skillSetRepository.save(skill8);

        SkillSet skill9 = new SkillSet();
        skill9.setDescription("Web Technologies");
        skill9.setSkillName("HTML5/CSS");
        skillSetRepository.save(skill9);

    }
    private void loadUod(){
        UnitOfDuration uod = new UnitOfDuration();
        uod.setUod("Years");
        unitOfDurationRepository.save(uod);

        UnitOfDuration uod2 = new UnitOfDuration();
        uod2.setUod("Months");
        unitOfDurationRepository.save(uod2);

        UnitOfDuration uod3 = new UnitOfDuration();
        uod3.setUod("Days");
        unitOfDurationRepository.save(uod3);


    }


}
