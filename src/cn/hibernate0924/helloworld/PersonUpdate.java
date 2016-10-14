package cn.hibernate0924.helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.hibernate0924.domain.Person;

public class PersonUpdate {

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
		 * 1.先把修改的那行提取出来 Serializable 是String和包装类型的父类
		 * session.get调用的是该对象默认的构造函数创建的对象，所以一个持久化类中必须包含一个默认的构造函数
		 */
		Person person = (Person) session.get(Person.class, 2L);
		System.out.println(person);
		person.setPsex("不详");
		/**
		 * 
		 * 在执行session.update的时候，hibernate内部会会为person对象去对照内存快照（副本），如果有属性发生变化
		 * 才要执行update语句，如果不改变，则不发出update语句
		 */

		// session.update(person); //可以不写,因为从数据库提取出来，已经是持久化状态

		/**
		 * 2.新创建一个person对象 把pid为1的放进去 设置修改 其他没有设置的字段会为空，因为是新的对象，不推荐使用
		 */
		// Person person1=new Person();
		// person1.setPid(1L);
		// person1.setPsex("女");
		// session.update(person1);

		// 提交事务
		transaction.commit();

		session.close();
	}

}
