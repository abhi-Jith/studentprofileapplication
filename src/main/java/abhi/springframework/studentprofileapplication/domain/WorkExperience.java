package abhi.springframework.studentprofileapplication.domain;

import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getWorkExperienceDescription() {
        return workExperienceDescription;
    }

    public void setWorkExperienceDescription(String workExperienceDescription) {
        this.workExperienceDescription = workExperienceDescription;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Integer getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public UnitOfDuration getUnitOfDuration() {
        return unitOfDuration;
    }

    public void setUnitOfDuration(UnitOfDuration unitOfDuration) {
        this.unitOfDuration = unitOfDuration;
    }
}
