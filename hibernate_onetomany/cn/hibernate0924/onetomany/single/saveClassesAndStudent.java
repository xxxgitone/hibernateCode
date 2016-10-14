package cn.hibernate0924.onetomany.single;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveClassesAndStudent {
	/**
	 * 3.保存班级的时候同时保存学生
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/onetomany/single/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		//不能设置学生外键，而且班级要重复设置
		Student student=new Student();
		student.setSname("班密");
		student.setDescription("班长旁边的，女士优先");
		session.save(student);
		
		Classes classes=new Classes();
		classes.setCname("0924java精品班");
		classes.setDescription("还是极品号");
		session.save(classes);
		
		transaction.commit();
		session.close();
	}

}
