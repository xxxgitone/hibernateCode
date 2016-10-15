package cn.hibernate0924.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * hql针对查询
 * @author Administrator
 *
 */
public class HQLTest {
	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		//加载配置文件
		configuration.configure("cn/hibernate0924/hql/hibernate.cfg.xml");
		//采用了工厂模式创建sessionFactory
		sessionFactory = configuration.buildSessionFactory();
	}
	
	/************************单表查询***********************************************/
	/**
	 * 查询classes表中所有数据
	 */
	@Test
	public void testClasses_All(){
		Session session=sessionFactory.openSession();
								//from 持久化类
		List<Classes> classesList=session.createQuery("from Classes").list();
		System.out.println(classesList.size());
		session.close();
	}
	
	/**
	 * 按照需求查询，但结构不怎么样
	 */
	@Test
	public void testClasses_Select(){
		Session session=sessionFactory.openSession();
		//用这种方法提取出来的：list里面存放的是object对象，Object[]每个元素的类型，取决于持久化类的属性的类型
		
		//报错：java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to cn.hibernate0924.hql.Classes
		
		List<Classes> classesList=session.createQuery("select cid,cname from Classes").list();
		
		for(Classes classes:classesList){
			System.out.println(classes.getCid());
		}
		
		session.close();
	}
	
	/**
	 * 投影查询
	 * 
	 * 在classes持久化类中添加一个构造函数
	 * 
	 * 利用带构造函数的select查询结构更好，在持久化类中必须提供构造函数，注意别忘了默认的构造函数
	 */
	@Test
	public void testClasses_Select_Constructor(){
		Session session=sessionFactory.openSession();
		//投影查询
		List<Classes> classesList=session.createQuery("select new cn.hibernate0924.hql.Classes(cid,cname) from Classes").list();
		
		for(Classes classes:classesList){
			System.out.println(classes.getCid());
		}
		//System.out.println(classesList.size());
		session.close();
	}
	
	@Test
	public void testClasses_Select_OrderBy(){
		Session session = sessionFactory.openSession();
		List classesList = session.createQuery("from Classes order by cid").list();
		classesList.size();
		session.close();
	}
	
	
	/*****************************一对多查询******************************************/
	/**
	 * 一对多：
	 * 		1.等值连接
	 * 		2.内连接
	 * 		3.左外连接
	 * 		4.迫切左外连接
	 * 		5.迫切右外连接
	 * 		6.带构造函数的select与迫切的关系
	 */
	
	/**
	 * 等值连接
	 * 	select c.*,s.* from classes c,student s where c.cid=s.cid
	 */
	@Test
	public void testSelect_Classes_Student_EQ(){
		Session session = sessionFactory.openSession();
		//结构不怎么好
		List classesList = session.createQuery("from Classes c,Student s where c.cid=s.classes.cid").list();
		System.out.println(classesList.size());
		session.close();
	}
	
	/**
	 * 迫切内连接
	 *  select c.*,s.* from classes c inner join student s on(c.cid=s.sid);
	 */
	@Test
	public void testSelect_Classes_Student_EQ_FETCH(){
		Session session = sessionFactory.openSession();
		//结构好  迫切的含义就是在join后面加一个fetch结构就好了     等值连接没有迫切说法
		List classesList = session.createQuery("from Classes c inner join fetch c.students s").list();
		System.out.println(classesList.size());
		session.close();
	}
	
	/**
	 * 迫切内连接
	 */
	@Test
	public void testSelect_Student_Classes_EQ_FETCH(){
		Session session = sessionFactory.openSession();
		List classesList = session.createQuery("from Student s inner join fetch s.classes c").list();
		System.out.println(classesList.size());
		session.close();
	}
	
	
	
	/*********************************************************************************************/
	/**
	 * 多对多
	 * 		1、等值连接
	 * 		2、内连接
	 * 		3、左外连接
	 * 		4、迫切左外连接
	 * 		5、迫切右外连接
	 *      6、带构造函数的select与迫切的关系
	 */
	
}
