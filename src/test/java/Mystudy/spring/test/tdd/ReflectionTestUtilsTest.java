package Mystudy.spring.test.tdd;

import Mystudy.spring.Application;
import Mystudy.spring.test.testProject.models.CollegeStudent;
import Mystudy.spring.test.testProject.models.StudentGrades;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest(classes = Application.class)
public class ReflectionTestUtilsTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    public void studentBeforeEach(){
        studentOne.setFirstname("Eric");
        studentOne.setLastname("Roby");
        studentOne.setEmailAddress("eric.roby@lub2code_school.com");
        studentOne.setStudentGrades(studentGrades);

        ReflectionTestUtils.setField(studentOne, "id", 1);
        ReflectionTestUtils.setField(studentOne, "studentGrades",
                new StudentGrades(new ArrayList<>(Arrays.asList(
                        100.0, 85.0, 76.50, 9.75
                ))));
    }

    @Test
    void getPrivateField(){
        Assertions.assertEquals(1, ReflectionTestUtils.getField(studentOne, "id"));
    }

    @Test
    void invokePrivateMethod(){
        Assertions.assertEquals("Eric 1",
                ReflectionTestUtils.invokeMethod(studentOne, "getFirstNameAndId"),
                "Fail private method not call");
    }
}
