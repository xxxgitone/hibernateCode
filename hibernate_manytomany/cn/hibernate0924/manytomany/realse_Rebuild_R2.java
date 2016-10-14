package cn.hibernate0924.manytomany;


import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class realse_Rebuild_R2 {
	/**
	 * 7.已经存在一个学生，已经存在多个课程，解除该学生和原来多个课程之间的关系，建立该学生和新的多个课程之  间的关系
	 * 		
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 
		 */
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/manytomany/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		//获取sid为5的学生
		Student student=(Student)session.get(Student.class, 5L);
		////等到cid为2、5的课程
		Course course2=(Course)session.get(Course.class, 2L);
		Course course5=(Course)session.get(Course.class, 5L);
		
		//得到该学生的所有课程
		Set<Course> courses=student.getCourses();
		for(Course course:courses){
			//在关系表中把sid为5，cid为1和3的移除
			if(course.getCid().longValue()==1||course.getCid().longValue()==3){
				courses.remove(course);  //移除
				break;
			}
		}
		
		courses.add(course2);
		courses.add(course5);
		
		transaction.commit();
		session.close();
		
		
		
	}

}
