package cn.hibernate0924.stateTest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.hibernate0924.domain.Person;

public class StateExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * 利用session.createQuery("from Person").list方法把person表的所有的数据全部提取出来
		 * 然后遍历list,把person中的属性做一些改变，最后提交事务，关闭session.
		 * 
		 */
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		List<Person> personList = session.createQuery("from Person").list();

		System.out.println(personList);
		for (Person person : personList) {
			person.setPname("小明"); // person已经是持久化状态
			// session.update(person); //不用
		}

		transaction.commit();

		session.close();
	}

}
