package cn.hibernate0924.helloworld;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.hibernate0924.domain.Person;

public class PersonQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		// 采用工厂模式创建sessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		List<Person> personList = session.createQuery("from Person").list();

		System.out.print(personList.size());

	}

}
