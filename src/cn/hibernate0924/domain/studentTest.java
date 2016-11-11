package cn.hibernate0924.domain;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class studentTest {
	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		//加载配置文件
		configuration.configure("hibernate.cfg.xml");
		//采用了工厂模式创建sessionFactory
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Test
	public void testexecuteUpdate(){
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		//在hibernate3以后 提供了一个高效的更新和删除操作的方法executeUpdate
		//更新
		//Query query=session.createQuery("updeate form Student s set s.name=:name where s.id=:id");
		
		//删除指定id的student对象
		Query query=session.createQuery("delete from Person p where p.pid=:pid");
		query.setLong("pid", 3);
		
		//返回删除或更新影响的行数
		int count=query.executeUpdate();
		System.out.println(count);
		
		transaction.commit();
	}
	
	
}
