package abhi.springframework.studentprofileapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String address;
    private String emailId;
    private Integer ssn;
    private String mobileNumber;

    @Lob
    private Byte[] image;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "studentProfile")
    private Set<Projects>projects=new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentProfile")
    private Set<WorkExperience> workExperiences= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentProfile")
    private Set<EducationalQualification> educationalQualifications=new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Aboutme aboutme;

    @Enumerated(value = EnumType.STRING)
    private CodingLevel codingLevel;

    @ManyToMany
    @JoinTable(name = "studentProfile_skillSet",
        joinColumns = @JoinColumn(name = "studentProfile_id"),
            inverseJoinColumns = @JoinColumn(name = "skillSet_id"))
    private Set<SkillSet>skillSets= new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Aboutme getAboutme() {
        return aboutme;
    }

    public void setAboutme(Aboutme aboutme) {
        this.aboutme = aboutme;
        aboutme.setStudentProfile(this);
    }

    public StudentProfile addEducationalQualification(EducationalQualification educationalQualification){
        educationalQualification.setStudentProfile(this);
        this.educationalQualifications.add(educationalQualification);
        return this;
    }

    public StudentProfile addProjects(Projects project){
        project.setStudentProfile(this);
        this.projects.add(project);
        return this;
    }


    public StudentProfile addWorkExperience(WorkExperience workExperience){
        workExperience.setStudentProfile(this);
        this.workExperiences.add(workExperience);
        return this;
    }

    public Set<EducationalQualification> getEducationalQualifications() {
        return educationalQualifications;
    }

    public void setEducationalQualifications(Set<EducationalQualification> educationalQualifications) {
        this.educationalQualifications = educationalQualifications;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Set<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(Set<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    public CodingLevel getCodingLevel() {
        return codingLevel;
    }

    public void setCodingLevel(CodingLevel codingLevel) {
        this.codingLevel = codingLevel;
    }

    public Set<SkillSet> getSkillSets() {
        return skillSets;
    }

    public void setSkillSets(Set<SkillSet> skillSets) {
        this.skillSets = skillSets;
    }
}
