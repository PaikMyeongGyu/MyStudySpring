package Mystudy.spring;

import Mystudy.spring.test.gradeProject.models.Grade;
import Mystudy.spring.test.gradeProject.models.HistoryGrade;
import Mystudy.spring.test.gradeProject.models.MathGrade;
import Mystudy.spring.test.gradeProject.models.ScienceGrade;
import Mystudy.spring.test.gradeProject.models.CollegeStudent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean(name = "collegeStudent")
//	@Scope(value = "prototype")
//	CollegeStudent getCollegeStudent(){
//		return new CollegeStudent();
//	}

//	/* New for Section 2.2 */
//	@Bean(name = "applicationExample")
//	ApplicationService getApplicationService() {
//		return new ApplicationService();
//	}
//
//	/* New for Section 2.2 */
//	@Bean(name = "applicationDao")
//	ApplicationDao getApplicationDao() {
//		return new ApplicationDao();
//	}
//
//	@Bean(name = "collegeStudent")
//	@Scope(value = "prototype")
//	CollegeStudent getCollegeStudent() {
//		return new CollegeStudent();
//	}

	@Bean
	@Scope(value = "prototype")
	CollegeStudent getCollegeStudent() {
		return new CollegeStudent();
	}

	@Bean
	@Scope(value = "prototype")
	Grade getMathGrade(double grade) {
		return new MathGrade(grade);
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("mathGrades")
	MathGrade getGrade() {
		return new MathGrade();
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("scienceGrades")
	ScienceGrade getScienceGrade() {
		return new ScienceGrade();
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("historyGrades")
	HistoryGrade getHistoryGrade() {
		return new HistoryGrade();
	}

}
