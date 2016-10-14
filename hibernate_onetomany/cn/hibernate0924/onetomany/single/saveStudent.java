package cn.hibernate0924.onetomany.single;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveStudent {
	
	public static void main(String[] args) {
		/**
		 * 2.保存学生
		 */
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/onetomany/single/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		
		Transaction transaction=session.beginTransaction();
		//这个操作只能插入一个student对象，但是在student表中，外键仍然为null
		Student student=new Student();
		student.setSname("班长");
		student.setDescription("有班密");
		
		session.save(student);
		transaction.commit();
		session.close();
	}

}
