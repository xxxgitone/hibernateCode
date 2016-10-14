package cn.hibernate0924.onetomany.single;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class saveClasses {
	/**
	 * 1.保存班级
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/onetomany/single/hibernate.cfg.xml");
		
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		Transaction transaction=session.beginTransaction();
		Classes classes=new Classes();
		classes.setCname("java极品就业班2");
		classes.setDescription("学java就是好a");
		session.save(classes);
		transaction.commit();
		session.close();

	}

}
