package Mystudy.spring.test.gradeProject.service;

import Mystudy.spring.test.gradeProject.models.CollegeStudent;
import Mystudy.spring.test.gradeProject.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDao;

    public void createStudent(String firstname, String lastname, String email){
        CollegeStudent student = new CollegeStudent(firstname, lastname, email);
        student.setId(0);
        studentDao.save(student);
    }

    public boolean checkIfStudentIsNull(int id){
        Optional<CollegeStudent> student = studentDao.findById(id);
        if(student.isPresent()){
            return true;
        }
        return false;
    }

    public void deleteStudent(int id){
        if(checkIfStudentIsNull(id)){
            studentDao.deleteById(id);
        }
    }

    public Iterable<CollegeStudent> getGradebook(){
        Iterable<CollegeStudent> collegeStudents = studentDao.findAll();
        return collegeStudents;
    }
}
