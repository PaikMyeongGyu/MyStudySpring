package Mystudy.spring.test.SpringBootUnitTest;

import Mystudy.spring.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
class CollegeStudentTest {

    private static int count = 0;

    @Value("${info.app.name")
    private String appInfo;

    @Value("${info.app.description")
    private String appDescription;

    @Value("${info.app.version")
    private String appVersion;

    @Value("${info.school.name")
    private String schoolName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach(){
        count = count + 1;
        System.out.println("Testing: " + appInfo + " which is " + appDescription +
                " Version: " + appVersion + ". Execution of test method " + count);
        student.setFirstname("Eric");
        student.setLastname("Roby");
        student.setEmailAddress("eric.roby@lub2code_school.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75)));
        student.setStudentGrades(studentGrades);
    }

    @DisplayName("Add grade results for student grades")
    @Test
    void addGradeResultsForStudentGrades(){
        // given
        assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }

    @DisplayName("Add grade results for student grades not equal")
    @Test
    void addGradeResultsForStudentGradesAssertNotEquals(){
        // given
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }

    @DisplayName("Is grade greater")
    @Test
    void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90,75),
                "failure - should be true");
    }

    @DisplayName("Is grade greater false")
    @Test
    void isGradeGreaterStudentGradesAssertFalse(){
        assertFalse(studentGrades.isGradeGreater(89,92),
                "failure - should be false");
    }

    @DisplayName("Check Null for student grades")
    @Test
    void checkNullForStudentGrades(){
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),
                "object - should not be Null");
    }

    @DisplayName("Create student without grade init")
    @Test
    void createStudentWithoutGradesInit(){
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstname("Chad");
        studentTwo.setLastname("Darby");
        studentTwo.setEmailAddress("chad.darby@lub2code_scholol.com");
        assertNotNull(studentTwo.getFirstname());
        assertNotNull(studentTwo.getLastname());
        assertNotNull(studentTwo.getEmailAddress());
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));

    }

    @Test
    void verifyStudentArePrototypes(){
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);

        assertNotSame(student, studentTwo);
    }

    @DisplayName("Find Grade Point Averae")
    @Test
    void findGradePointAverage(){
        assertAll("Testing all assertEquals",
                () -> assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(
                        student.getStudentGrades().getMathGradeResults())),
                () -> assertEquals(88.31, studentGrades.findGradePointAverage(
                        student.getStudentGrades().getMathGradeResults()))
        );
    }
}