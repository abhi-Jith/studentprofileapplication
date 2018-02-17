package abhi.springframework.studentprofileapplication.domain;

import javax.persistence.*;

@Entity
public class EducationalQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String qualification;
    private String institutionName;
    private String year;

    //todo add later
    //private Byte[] certificateImage;

    @ManyToOne
    private StudentProfile studentProfile;

    public EducationalQualification() {
    }

    public EducationalQualification(String qualification, String institutionName, String year) {
        this.qualification = qualification;
        this.institutionName = institutionName;
        this.year = year;
    }

    public EducationalQualification(String qualification, String institutionName, String year, StudentProfile studentProfile) {
        this.qualification = qualification;
        this.institutionName = institutionName;
        this.year = year;
       // this.certificateImage = certificateImage;
        this.studentProfile = studentProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

//    public Byte[] getCertificateImage() {
//        return certificateImage;
//    }
//
//    public void setCertificateImage(Byte[] certificateImage) {
//        this.certificateImage = certificateImage;
//    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
}
