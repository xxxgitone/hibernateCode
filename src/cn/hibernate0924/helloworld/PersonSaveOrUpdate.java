package cn.hibernate0924.helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.hibernate0924.domain.Person;

public class PersonSaveOrUpdate {

	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		// Session session=sessionFactory.openSession();
		// Transaction transaction=session.beginTransaction();
		// Person person=new Person(); //临时状态
		// person.setPname("李四");//临时状态
		// person.setPsex("女");//临时状态
		// session.save(person);//将临时状态转化为持久状态
		// person.setPname("张三");//临时状态
		// //session.update(person);//将临时状态转化为持久状态，所以在此处代码无用
		//
		// transaction.commit();//同步到数据库
		//
		// session.close(); //托管状态

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Person person = new Person(); // 临时状态
		person.setPname("李四");
		person.setPsex("女");// 持久化状态
		person.setPname("张三");
		transaction.commit();
		session.close(); // 当session关闭后，事务环境不存在了

		session = sessionFactory.openSession();// 打开了有个新的session，这个时候并没有保存person对象
		Transaction transaction2 = session.beginTransaction();
		session.update(person);// person中的id已经有值了
		transaction2.commit();
		session.close();

	}

}
