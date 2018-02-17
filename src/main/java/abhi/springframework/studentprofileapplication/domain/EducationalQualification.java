package abhi.springframework.studentprofileapplication.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(exclude = "studentProfile")
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


//    public Byte[] getCertificateImage() {
//        return certificateImage;
//    }
//
//    public void setCertificateImage(Byte[] certificateImage) {
//        this.certificateImage = certificateImage;
//    }

}
