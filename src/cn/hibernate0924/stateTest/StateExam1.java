package cn.hibernate0924.stateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.hibernate0924.domain.Person;

public class StateExam1 {

	public static void main(String[] args) {
		/**
		 * 新创建一个person对象，执行save方法，再关闭session,
		 * 再重新打开一个session,让person对象的属性值发生改变，把改变后的结果同步到数据库中
		 * 
		 */
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Person person = new Person();
		person.setPname("小红");
		person.setPsex("女");
		session.save(person);
		transaction.commit();
		session.close();

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		person.setPsex("不祥");
		session.update(person); // 一定要用
		transaction.commit();
		session.close();
	}

}
