package abhi.springframework.studentprofileapplication.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EducationalQualificationTest {
    EducationalQualification educationalQualification;

    @Before
    public void setUp() throws Exception {
        educationalQualification= new EducationalQualification();
    }

    @Test
    public void getId() throws Exception {
        Long value = 4l;
        educationalQualification.setId(value);

        assertEquals(value,educationalQualification.getId());
    }

    @Test
    public void getQualification() throws Exception {
        String qualification ="B.Tech";
         educationalQualification.setQualification(qualification);
         assertEquals(qualification,educationalQualification.getQualification());
    }

    @Test
    public void getInstitutionName() throws Exception {
    }

}