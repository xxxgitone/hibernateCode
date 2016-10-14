package cn.hibernate0924.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class saveCourse {
	/**
	 * 保存课程
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
		course.setCname("生理卫生");
		course.setDescription("将的都是卫生");
		session.save(course);
		
		transaction.commit();
		session.close();
		

		
	}

}
