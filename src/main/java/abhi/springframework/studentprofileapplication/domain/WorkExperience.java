package abhi.springframework.studentprofileapplication.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(exclude = "studentProfile")
@Entity
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String positionName;
    private String companyAddress;
    private Integer referenceNumber;


    @Lob
    private String workExperienceDescription;

    @ManyToOne
    private StudentProfile studentProfile;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfDuration unitOfDuration;

    public WorkExperience() {
    }
//    public WorkExperience(String workExperienceDescription,StudentProfile studentProfile){
//        this.workExperienceDescription=workExperienceDescription;
//        this.studentProfile=studentProfile;
//    }


    public WorkExperience(String companyName, String positionName, String companyAddress, Integer referenceNumber, String workExperienceDescription, UnitOfDuration unitOfDuration) {
        this.companyName = companyName;
        this.positionName = positionName;
        this.companyAddress = companyAddress;
        this.referenceNumber = referenceNumber;
        this.workExperienceDescription = workExperienceDescription;
        this.unitOfDuration = unitOfDuration;
    }

    public WorkExperience(String companyName, String positionName, String companyAddress, Integer referenceNumber,
                          String workExperienceDescription, StudentProfile studentProfile, UnitOfDuration unitOfDuration) {
        this.companyName = companyName;
        this.positionName = positionName;
        this.companyAddress = companyAddress;
        this.referenceNumber = referenceNumber;
        this.workExperienceDescription = workExperienceDescription;
        this.studentProfile = studentProfile;
        this.unitOfDuration = unitOfDuration;
    }
}
