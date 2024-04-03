package Mystudy.spring.test.tdd;

import Mystudy.spring.Application;
import Mystudy.spring.test.testProject.dao.ApplicationDao;
import Mystudy.spring.test.testProject.models.CollegeStudent;
import Mystudy.spring.test.testProject.models.StudentGrades;
import Mystudy.spring.test.testProject.service.ApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Application.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    // 스프링 부트가 제공하는 것이 아님.
//    @Mock
//    private ApplicationDao applicationDao;
//
//    @InjectMocks
//    private ApplicationService applicationService;

    // 스프링 부트가 제공하는 기능
    @MockBean
    private ApplicationDao applicationDao;

    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    public void beforeEach(){
        studentOne.setFirstname("Eric");
        studentOne.setLastname("Roby");
        studentOne.setEmailAddress("eric.roby@luv2code_school.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @DisplayName("When & Verify")
    @Test
    void assertEqualsTestAddGrades(){
        // given
        when(applicationDao.addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults())).thenReturn(100.00);
        // when // then
        assertEquals(100, applicationService.addGradeResultsForSingleClass(
                studentOne.getStudentGrades().getMathGradeResults()));
        // 메서드 호출 여부 테스트
        verify(applicationDao).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
        // 메서드 호출 횟수 테스트
        verify(applicationDao, times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }

    @DisplayName("Find Gpa")
    @Test
    void assertEqualsTestFindGpa(){
        // given
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults()))
                .thenReturn(88.31);
        // when // then
        assertEquals(88.31, applicationService.findGradePointAverage(
                studentOne.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Not Null")
    @Test
    void testAssertNotNull(){
        // given
        when(applicationDao.checkNull(studentGrades.getMathGradeResults()))
                .thenReturn(true);
        // when // then
        assertNotNull(applicationService.checkNull(studentOne.getStudentGrades()
                .getMathGradeResults()), "Object should not be null");
    }

    @DisplayName("Throw runtime error")
    @Test
    void throwRuntimeError(){
        // given
        CollegeStudent nullStudent = (CollegeStudent) context.getBean("collegeStudent");
        doThrow(new RuntimeException()).when(applicationDao).checkNull(nullStudent);

        // when // then
        assertThrows(RuntimeException.class, () ->{
            applicationService.checkNull(nullStudent);
        });

        verify(applicationDao, times(1)).checkNull(nullStudent);
    }

    @DisplayName("Multiple Stubbing")
    @Test
    void stubbingConsecutiveCalls(){
        // given
        CollegeStudent nullStudent = (CollegeStudent) context.getBean("collegeStudent");

        // when // then
        when(applicationDao.checkNull(nullStudent))
                .thenThrow(new RuntimeException())
                .thenReturn("Do not throw exception second time");

        assertThrows(RuntimeException.class, () ->{
            applicationService.checkNull(nullStudent);
        });

        assertEquals("Do not throw exception second time",
                applicationService.checkNull(nullStudent));

        verify(applicationDao, times(2)).checkNull(nullStudent);
    }
}
