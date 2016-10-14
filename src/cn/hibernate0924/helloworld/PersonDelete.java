package cn.hibernate0924.helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.hibernate0924.domain.Person;

public class PersonDelete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		// 采用工厂模式创建sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		/**
		 * 1.推荐
		 */
		Person person = (Person) session.get(Person.class, 1L);
		/**
		 * 2.
		 */
		// Person person=new Person();
		// person.setPid(2L);

		session.delete(person);

		// 提交事务
		transaction.commit();

		session.close();
	}

}
