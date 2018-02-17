package abhi.springframework.studentprofileapplication.bootstrap;

import abhi.springframework.studentprofileapplication.domain.*;
import abhi.springframework.studentprofileapplication.repositories.SkillSetRepository;
import abhi.springframework.studentprofileapplication.repositories.StudentProfileRepository;
import abhi.springframework.studentprofileapplication.repositories.UnitOfDurationRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentProfileBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private SkillSetRepository skillSetRepository;
    private StudentProfileRepository studentProfileRepository;
    private UnitOfDurationRepository unitOfDurationRepository;

    public StudentProfileBootstrap(SkillSetRepository skillSetRepository, StudentProfileRepository studentProfileRepository, UnitOfDurationRepository unitOfDurationRepository) {
        this.skillSetRepository = skillSetRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.unitOfDurationRepository = unitOfDurationRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        studentProfileRepository.saveAll(getStudentProfile());
    }

    private List<StudentProfile> getStudentProfile() {
        List<StudentProfile> studentProfiles = new ArrayList<>();

        //SkillSet
        Optional<SkillSet> javaSkillSetOptional = skillSetRepository.findBySkillName("Java J2EE");
        if (!javaSkillSetOptional.isPresent()) {
            throw new RuntimeException("Java J2EE skill not found");
        }

        Optional<SkillSet> springFrameworkskillSetOptional = skillSetRepository.findBySkillName("Spring Framework");
        if (!springFrameworkskillSetOptional.isPresent()) {
            throw new RuntimeException("Spring Framework skill not found");
        }

        Optional<SkillSet> hibernateSkillSetOptional = skillSetRepository.findBySkillName("Hibernate");
        if (!hibernateSkillSetOptional.isPresent()) {
            throw new RuntimeException("Hibernate skill not found");
        }

        Optional<SkillSet> thymeleafSkillSetOptional = skillSetRepository.findBySkillName("Thymeleaf");
        if (!thymeleafSkillSetOptional.isPresent()) {
            throw new RuntimeException("Thymeleaf skill not found");
        }

        Optional<SkillSet> cplusplusSkillSetOptional = skillSetRepository.findBySkillName("C++");
        if (!cplusplusSkillSetOptional.isPresent()) {
            throw new RuntimeException("C++ skill not found");
        }

        Optional<SkillSet> cLanguageSkillSetOptional = skillSetRepository.findBySkillName("C Language");
        if (!cLanguageSkillSetOptional.isPresent()) {
            throw new RuntimeException("C Language skill not found");
        }

        Optional<SkillSet> angularJsSkillSetOptional = skillSetRepository.findBySkillName("Angular JS");
        if (!angularJsSkillSetOptional.isPresent()) {
            throw new RuntimeException("Angular JS skill not found");
        }

        Optional<SkillSet> nodeJsSkillSetOptional = skillSetRepository.findBySkillName("Node JS");
        if (!nodeJsSkillSetOptional.isPresent()) {
            throw new RuntimeException("Node JS skill not found");
        }

        Optional<SkillSet> html5CssSkillSetOptional = skillSetRepository.findBySkillName("HTML5/CSS");
        if (!html5CssSkillSetOptional.isPresent()) {
            throw new RuntimeException("HTML5/CSS skill not found");
        }

        //get() Skillsets
        SkillSet javaJ2ee = javaSkillSetOptional.get();
        SkillSet springFramework = springFrameworkskillSetOptional.get();
        SkillSet hibernate = hibernateSkillSetOptional.get();
        SkillSet thymeleaf = thymeleafSkillSetOptional.get();
        SkillSet cplusplus = cplusplusSkillSetOptional.get();
        SkillSet cLanguage = cLanguageSkillSetOptional.get();
        SkillSet angularJs = angularJsSkillSetOptional.get();
        SkillSet nodeJs = nodeJsSkillSetOptional.get();
        SkillSet html5Css = html5CssSkillSetOptional.get();

        //Unit of duration
        Optional<UnitOfDuration> yearUnitOfDurationOptional = unitOfDurationRepository.findByUod("Years");
        if (!yearUnitOfDurationOptional.isPresent()) {
            throw new RuntimeException("Years uod is not Found");
        }

        Optional<UnitOfDuration> monthsUnitOfDurationOptional = unitOfDurationRepository.findByUod("Months");
        if (!monthsUnitOfDurationOptional.isPresent()) {
            throw new RuntimeException("Months uod is not Found");
        }

        Optional<UnitOfDuration> daysUnitOfDurationOptional = unitOfDurationRepository.findByUod("Days");
        if (!daysUnitOfDurationOptional.isPresent()) {
            throw new RuntimeException("Days uod is not Found");
        }

        //get() unit of Durations
        UnitOfDuration years = yearUnitOfDurationOptional.get();
        UnitOfDuration months = monthsUnitOfDurationOptional.get();
        UnitOfDuration days = daysUnitOfDurationOptional.get();

        //StudentProfile 1- Abhijith SatheeshKumar
        StudentProfile abhiStudentProfile = new StudentProfile();
        abhiStudentProfile.setFirstName("Abhijith");
        abhiStudentProfile.setLastName("SatheeshKumar");
        abhiStudentProfile.setAddress("912 N Charles Street" +
                "Apt # 72" +
                "Macomb" +
                "IL-61455");
        abhiStudentProfile.setGender("Male");
        abhiStudentProfile.setDob("09-25-1993");
        abhiStudentProfile.setMobileNumber("3093636827");
        abhiStudentProfile.setEmailId("s.abhijith123@gmail.com");
        abhiStudentProfile.setSsn(23290212);
        abhiStudentProfile.setCodingLevel(CodingLevel.MODERATE);

        Aboutme abhiAboutme = new Aboutme();
        abhiAboutme.setAboutMe("Am a Computer Science master's student actively looking out for a full time opportunity." +
                "\n" +
                "My programming interest lies in Java J2EE and Spring Framework 5.0." +
                "\n" +
                "I have the urge to learn new technologies and I believe that I have the required skills to do so." +
                "\n" +
                "I am very flexible and hardworking in my profession.");

        abhiStudentProfile.setAboutme(abhiAboutme);
      //  abhiAboutme.setStudentProfile(abhiStudentProfile);

        abhiStudentProfile.addEducationalQualification(new EducationalQualification("Bachelor of Engineering in Computer Science",
                "Anna University", "2011-2015"));
        abhiStudentProfile.addEducationalQualification(new EducationalQualification("Master of Science in Computer Science",
                "Western Illinois University", "2016-2018"));


        abhiStudentProfile.addWorkExperience(new WorkExperience("Unisoft Technology Limited", "Junior Java Developer",
                "32nd Street, Indiranagar, Banglore-India", 20294532, "Developed web and Enterprise application using Java and J2EE technology. \n" +
                "• Implemented authorization, authentication using Spring Security. \n" +
                "• Used spring frameworks to implement IOC and DI. \n" +
                "• Worked on the agile environment for developing application. \n" +
                "• Implemented Bean validation and AOP for logging. \n" +
                "• Perform unit testing using JUnit and Mockito. \n" +
                "• Implemented RESTful web service for data retrieval. \n" +
                "• Implemented ORM in the persistence layer using hibernate frameworks. \n" +
                "• Used Design patterns to develop robust applications with optimal functionality. \n" +
                "• Worked on Time and attendance module and implemented various features like recording, tracking, monitoring, evaluating employee's working times, absence etc. \n" +
                "• Extensive Use of Session Management, View state and Cookie(s) to maintain state in the applications. \n" +
                "• Wrote and modified stored procedures, triggers, views and custom functions and query optimization with SQL Server Profiler. \n" +
                "• Used Jenkins for continuous integration. \n" +
                "• Dynamic reports using Jasper Reports for easy and safe documentation. \n" +
                "• Code reviewing after the development. (Peer review). ", months));


        abhiStudentProfile.getSkillSets().add(javaJ2ee);
        abhiStudentProfile.getSkillSets().add(springFramework);
        abhiStudentProfile.getSkillSets().add(hibernate);
        abhiStudentProfile.getSkillSets().add(thymeleaf);
        abhiStudentProfile.getSkillSets().add(cplusplus);
        abhiStudentProfile.getSkillSets().add(cLanguage);
        abhiStudentProfile.getSkillSets().add(angularJs);
        abhiStudentProfile.getSkillSets().add(nodeJs);
        abhiStudentProfile.getSkillSets().add(html5Css);


        abhiStudentProfile.addProjects(new Projects("This project is build on Java using Spring Framework MVC.\n" +
                "This project is used to view each student profile.\n " +
                "Each student can update delete or view his profile.\n" +
                "This can be used by faculty or placement coordinators to fliter and keep track of students.\n",
                "Student Profile Application","Technoligies used: Spring Framework 5.0, Hibernate, JPA 2.01, H2 database.\n" ));


        studentProfiles.add(abhiStudentProfile);

        //StudentProfile 2- David John
        StudentProfile davidStudentProfile = new StudentProfile();
        davidStudentProfile.setFirstName("David");
        davidStudentProfile.setLastName("John");
        davidStudentProfile.setAddress("1023 Kings Drive" +
                "Apt 21" +
                "Tampa" +
                "FL-33441");
        davidStudentProfile.setGender("Male");
        davidStudentProfile.setDob("01-05-1987");
        davidStudentProfile.setMobileNumber("2013452827");
        davidStudentProfile.setEmailId("davidJohn42@gmail.com");
        davidStudentProfile.setSsn(12312232);
        davidStudentProfile.setCodingLevel(CodingLevel.PROFESSIONAL);

        Aboutme davidAboutme = new Aboutme();
        davidAboutme.setAboutMe("Am a Senior Level Java Developer with more than 8 years of work experience in reputed organisations." +
                "\n" +
                "Am strong  in Java / J2EE programming background developing Web based application using Agile methodologies practices. " +
                "\n" +
                "With my experience I believe that I can be assigned with any project in any stage of development." +
                "\n" +
                "I am a team leader and encourage others to get the assigned task completed on time.");

        davidStudentProfile.setAboutme(davidAboutme);
        //davidAboutme.setStudentProfile(davidStudentProfile);

        davidStudentProfile.addEducationalQualification(new EducationalQualification("Bachelor of Engineering in Computer Science",
                "University of Texas", "2003-2007"));
        davidStudentProfile.addEducationalQualification(new EducationalQualification("Master of Science in Computer Science",
                "Harvard University", "2009-2011"));


        davidStudentProfile.addWorkExperience(new WorkExperience("Tenet Health Care", "Senior Java Developer",
                "Dallas, Texas", 10210234, " Strong Programming Skills in designing and implementation of multi-tier applications using JAVA with various J2EE technologies JSP, Servlets, JDBC, EJB, Struts, JMS SOAP/Rest Web Services, Spring, Hibernate, JSF and Angular JS. \n" +
                "• Hands on experience with different Enterprise frameworks like spring, Struts, Axis2, Log4j. \n" +
                "• Expertise in programming with Spring Framework, Spring dependency injection, handler mappings with Spring MVC annotations, Spring AOP, Spring DAO and ORM Integration. \n" +
                "• Sound knowledge of Object Relation mapping tools Hibernate. \n" +
                "• Experienced in working with back end layer using SQL, PL/SQL, JDBC, JPA and Hibernate. \n" +
                "• Excellent knowledge of databases and developing Hibernate framework based DAO layer using ORM concepts and JPA annotations for persisting and retrieving data through MySQL and Oracle databases \n" +
                "• Expertise in XML based Web Services Technologies like SOAP, WSDL, JAXP parser to transfer and transform data between applications based on SOA (Service Oriented Architecture) \n" +
                "• Experience implementing Jersey RESTful web services with JAX-RS annotation \n" +
                "• Working Experience in designing the Front-end pages usingJSF, Angular JS, Java Script, JQuery, HTML, CSS, XML. \n" +
                "• Experience developing Service (EJB) components for middle tier component to implement business logic and deploying on JBOSS web server \n" +
                "• Designed and developed various middleware integration components using various messaging technologies like JMS, Web sphere MQ and Mule ESB. \n" +
                "• Have used Several Application Servers such as Web Logic,Web Sphere, JBOSS and web servers like Tomcat. \n" +
                "• Expertise in testing with and deploying applications using Ant, Maven and Jenkins. \n" +
                "• Experience working on UNIX, LINUX and Windows platforms. \n" +
                "• Developed user interface using JSTL, Struts tag libraries,HTMLand wrote client side validation scripts usingJava Script. \n" +
                "• Performed code enhancements, new feature implementation, and unit testing of the code following best practices. \n" +
                "• Expertise on J2EE Design patterns and Core Java Design patterns like MVC, Factory, Session facade, Singleton, Front Controller, Business Delegate, Service Locator, VO, DAO. \n" +
                "• Hands on experience with Object Oriented Design (OOD) and developing applications using UML. \n" +
                "• Strong experience working with databases (Oracle, Microsoft SQL Server, MYSQL, DB2) and programming languages SQL, PL/SQL in developing Views, Stored Procedures, Functions, Triggers and Joins. \n" +
                "• Experience in various XML technologies like XSL, XSLT, XPath, XSD, DTD, and XQuery. \n" +
                "• Very good working knowledge on configuration management tools likeSVN, GIT and CSV. ", years));

        davidStudentProfile.addProjects(new Projects("This project involves developing an application using which Auto Insurance companies can manage the repair shops in their network. This application allows insurance company users to add shops to their network terminate and suspend the shop from their network. Allows the approval process electronic using DocuSign tool. It provides an interface to maintain the shops profile. In short, makes the shop network management smooth, simple and easy. \n" +
                "Responsibilities: \n" +
                "• Involved in various phases of Software Development Life Cycle (SDLC) as requirement gathering, data modeling, analysis, architecture design & development for the project. \n" +
                "• Used elastic search for reading non transactional data \n" +
                "• Used Spring 4.x Framework for dependency injection, security features and integrated with the Hibernate framework. Developed user interface using JSP with Java Beans, JSTL and Custom Tag Libraries and Ajax to speed the application and Google maps for the maps and geocoding. \n" +
                "• Exposed web services using RESTful services. \n" +
                "• We used Spring MVC for web layer. For styling we used CSS3. Used javascript with iQuery libraries and other third party javascript libraries like moment.js to serve the purpose. Worked on javascript api for Google Maps to provide location and mapping services. Used Ajax calls to make User experience smooth. \n" +
                "• Implemented various design patterns in the project such as Business Delegate, Data Transfer Object, Data Access Object, Service Locator, Façade, Factory and Singleton. \n" +
                "• Validated users input using regular expressions and developed Maven to clean, compile, copy, deploy, generate configuration files using filters, doc, jar, war files. \n" +
                "• Used JUnit for unit testing. Developed Use case, Sequence and class diagrams using UML and Microsoft Visio. Monitored error logs using logback with SLF4j and used SVN for version maintenance. ","Network Management",
                "Java, J2EE, Spring 4.x, Hibernate 4.x , Eclipse , Oracle WebLogic Server 11g (10.3.5), JavaScript, jQuery, Oracle 11g, Elastic Search, Google Maps, SQL developer, UML, Microsoft Visio, JUnit 4, SVN, Linux, logback with SLF4j and MAVEN \n"));


        davidStudentProfile.getSkillSets().add(javaJ2ee);
        davidStudentProfile.getSkillSets().add(springFramework);
        davidStudentProfile.getSkillSets().add(hibernate);
        davidStudentProfile.getSkillSets().add(angularJs);
        davidStudentProfile.getSkillSets().add(nodeJs);
        davidStudentProfile.getSkillSets().add(html5Css);

        studentProfiles.add(davidStudentProfile);

        return studentProfiles;

    }
}
