package cn.hibernate0924.helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.hibernate0924.domain.Person;

public class PersonDo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person person = new Person();
		// person.setPid(9L);
		person.setPname("wang");
		person.setPsex("女");
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
