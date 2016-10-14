package cn.hibernate0924.manytomany;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveCourse_R {
	/**
	 * 6.已经存在一个学生，新建一个课程，并且建立该学生和该课程之间的关系
	 * 		1.新建一个课程
	 * 		2.查找学生
	 * 		3.在学生原有的课程基础上添加一门课程
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/manytomany/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Course course=new Course();
		course.setCname("项目课");
		course.setDescription("项目实战");
		
		Student student=(Student)session.get(Student.class, 5L);
		
		Set<Course> courses=student.getCourses();//获得该学生所有课程 
		
		courses.add(course);
		
		
		transaction.commit();
		session.close();
	}

}
