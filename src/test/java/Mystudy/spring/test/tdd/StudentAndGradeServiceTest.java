package Mystudy.spring.test.tdd;

import Mystudy.spring.test.gradeProject.models.CollegeStudent;
import Mystudy.spring.test.gradeProject.repository.StudentDao;
import Mystudy.spring.test.gradeProject.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @BeforeEach
    public void setupDatabase(){
        CollegeStudent collegeStudent = new CollegeStudent("Eric", "Roby", "eric.roby@lub2code_school.com");
        studentDao.save(collegeStudent);
    }

    @Test
    public void createStudentService(){
        studentService.createStudent("Chad", "Darby", "chad.darby@lub2code_school.com");
        CollegeStudent student = studentDao.findByEmailAddress("chad.darby@lub2code_school.com");
        Assertions.assertEquals("chad.darby@lub2code_school.com", student.getEmailAddress(), "find by email");
    }

    @Test
    public void isStudentNullCheck(){
        Assertions.assertTrue(studentService.checkIfStudentIsNull(1));
        Assertions.assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Test
    public void deleteStudentService(){
        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);

        Assertions.assertTrue(deletedCollegeStudent.isPresent(), "Return True");

        studentService.deleteStudent(1);

        deletedCollegeStudent = studentDao.findById(1);

        Assertions.assertFalse(deletedCollegeStudent.isPresent(), "Return False");
    }

    @Test
    public void getGradebookService(){
        Iterable<CollegeStudent> iterableCollegeStudents = studentService.getGradebook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for (CollegeStudent collegeStudent : iterableCollegeStudents){
            collegeStudents.add(collegeStudent);
        }

        Assertions.assertEquals(1, collegeStudents.size());
    }

    @AfterEach
    public void setupAfterTransaction(){
        jdbc.execute("DELETE from student");
    }
}
