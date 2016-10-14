package cn.hibernate0924.manytomany;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class realse_R_C {
	/**
	 * 9.解除该课程和所有学生之间的关系，在重新建立该课程和一些新的学生之间的关系
	 * 说明：
	 * 		1.必须由学生来维护关系
	 * 		2.已知条件是课程
	 * 			cid-->course-->set<student>-->遍历每一个student-->从每一个student中得到所有的课程
	 * 			-->找到移除的课程
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/manytomany/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Course course=(Course)session.get(Course.class, 3L);
		
		Set<Student> students=course.getStudents();
		for(Student student:students){
			Set<Course> courses=student.getCourses();
			for(Course course2:courses){
				if(course2.getCid().longValue()==3){
					courses.remove(course2);
					break;
				}
			}
		}
		
		Student student4=(Student)session.get(Student.class, 4L);
		Student student5=(Student)session.get(Student.class, 5L);
		
		student4.getCourses().add(course);
		student5.getCourses().add(course);
		
		transaction.commit();
		session.close();
	}

}
