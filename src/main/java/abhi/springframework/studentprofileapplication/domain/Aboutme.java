package abhi.springframework.studentprofileapplication.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "studentProfile")
@Entity
public class Aboutme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String aboutMe;

    @OneToOne
    private StudentProfile studentProfile;

}
