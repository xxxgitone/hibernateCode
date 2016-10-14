package cn.hibernate0924.manytomany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveCourse_Cascade_student_R {
	/**
	 * 保存课程级并保存学生，并且建立关系
	 * 	 因为student维护关系，多以将student配置文件中cascade设置为save-update，可以提高效率
	 *    
		
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/manytomany/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		//新建课程
		Course course=new Course();
		course.setCname("java高级");
		course.setDescription("框架讲解");
		Set<Course> courses=new HashSet<Course>();
		courses.add(course);
		
		//新建学生
		Student student=new Student();
		student.setSname("陈七");
		student.setDescription("努力");
		
		//通过学生建立与课程的关系
		student.setCourses(courses);
		
		session.save(student);

		
	
		
		transaction.commit();
		session.close();
	}

}
