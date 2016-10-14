package cn.hibernate0924.lazy;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.hibernate0924.session.cache.Classes;
import cn.hibernate0924.session.cache.Student;

/**
 * 懒加载
 * 		针对数据库中的大数据，不希望特别早加载到内存中，到用的时候在加载
 * 		类的懒加载
 * 		集合的懒加载
 * 		单端关联的懒加载
 * @author Administrator
 *
 */
public class LazyTest {
	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		//加载配置文件
		configuration.configure("cn/hibernate0924/session/cache/hibernate.cfg.xml");
		//采用了工厂模式创建sessionFactory
		sessionFactory = configuration.buildSessionFactory();
	}
	/**
	 * 类的懒加载
	 * 		1.在默认情况下，类执行的就是懒加载
	 * 		2.只有使用了load方法才能使用
	 * 		3.如果在相应的映射文件中，设置<class>的lazy="false" 将失去懒加载效果 
	 */
	@Test
	public void testLoad(){
		Session session=sessionFactory.openSession();
		Classes classes=(Classes)session.load(Classes.class, 1L);//使用get直接加载
		
		
		
		String cname=classes.getCname(); //在这一步才发出sql语句，用到的时候才加载，
		
		
		session.close();//将这句话放前面会抛异常
	}
	
	/**
	 * 
	 * 		集合的懒加载
	 *   针对一多对的情况或者多对多的情况
	 *     根据一方加载set集合，决定在什么时候给set集合填充数据
	 *     lazy="":
	 *     
	 *     true
	 *        在遍历集合中的每一个元素的时候发出sql语句
	 *     false
	 *        在得到集合的时候，发出sql语句
	 *     extra
	 *        students.size()这个时候用extra仅仅能够得到大小
	 */
	@Test
	public void testCollectionLazy(){
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Classes classes=(Classes)session.load(Classes.class, 1L);//使用get直接加载
		
		Set<Student> students=classes.getStudents();
		//System.out.print(students.size());
		
		for(Student student:students){
			System.out.print(student.getSname());  //在这一步才会发出sql语句
		}
		
		
		transaction.commit();
		session.close();//将这句话放前面会抛异常
	}
	
	/**
	 * 单端关联
	 *   根据多的一方加载一的一方
	 *   lazy="":
	 *     false
	 *     proxy  就相当于true
	 *     no-proxy
	 *      根据多的一方加载一的一方数据量特别少，所以怎么样都行
	 */
}
