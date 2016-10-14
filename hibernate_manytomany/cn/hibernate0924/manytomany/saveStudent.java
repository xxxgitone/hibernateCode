package cn.hibernate0924.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveStudent {
	/**
	 * 保存学生
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/manytomany/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Student student=new Student();
		student.setSname("李刘");
		student.setDescription("哈哈乱写的");
		
		session.save(student);
		
		transaction.commit();
		session.close();
	}

}
