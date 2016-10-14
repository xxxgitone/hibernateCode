package cn.hibernate0924.helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.hibernate0924.domain.Person;

public class PersonTest {
	@Test
	public void testSavePerson() {

		Person person = new Person();
		person.setPid(9L);
		person.setPname("er");
		person.setPsex("op");
		System.out.println(person.toString());
		Configuration configuration = new Configuration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");

		// 采用工厂模式创建sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.save(person);

		// 提交事务
		transaction.commit();

		session.close();

	}

}
