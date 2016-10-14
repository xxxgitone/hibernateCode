package cn.hibernate0924.fetch;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


/**
 * 抓取政策
 * 	1.根据一个对象怎么样提取它的关联对象
 * @author Administrator
 *
 */
public class fetchTest {
	private static SessionFactory sessionFactory;
	static{
		Configuration configuration = new Configuration();
		//加载配置文件
		configuration.configure("cn/hibernate0924/fetch/hibernate.cfg.xml");
		//采用了工厂模式创建sessionFactory
		sessionFactory = configuration.buildSessionFactory();
	}
	
	/**
	 * 
	 * 		查询每一个班级的学生
	 * 
	 *  Hibernate: select classes0_.cid as cid0_, classes0_.cname as cname0_, classes0_.description as descript3_0_ from Classes classes0_
		王五
		慕容
		陈七
		李刘
		Hibernate: select students0_.cid as cid0_1_, students0_.sid as sid1_, students0_.sid as sid1_0_, students0_.sname as sname1_0_, students0_.description as descript3_1_0_, students0_.cid as cid1_0_ from Student students0_ where students0_.cid=?
		Hibernate: select students0_.cid as cid0_1_, students0_.sid as sid1_, students0_.sid as sid1_0_, students0_.sname as sname1_0_, students0_.description as descript3_1_0_, students0_.cid as cid1_0_ from Student students0_ where students0_.cid=?
		Hibernate: select students0_.cid as cid0_1_, students0_.sid as sid1_, students0_.sid as sid1_0_, students0_.sname as sname1_0_, students0_.description as descript3_1_0_, students0_.cid as cid1_0_ from Student students0_ where students0_.cid=?
		Hibernate: select students0_.cid as cid0_1_, students0_.sid as sid1_, students0_.sid as sid1_0_, students0_.sname as sname1_0_, students0_.description as descript3_1_0_, students0_.cid as cid1_0_ from Student students0_ where students0_.cid=?
		Hibernate: select students0_.cid as cid0_1_, students0_.sid as sid1_, students0_.sid as sid1_0_, students0_.sname as sname1_0_, students0_.description as descript3_1_0_, students0_.cid as cid1_0_ from Student students0_ where students0_.cid=?
		Hibernate: select students0_.cid as cid0_1_, students0_.sid as sid1_, students0_.sid as sid1_0_, students0_.sname as sname1_0_, students0_.description as descript3_1_0_, students0_.cid as cid1_0_ from Student students0_ where students0_.cid=?
		Hibernate: select students0_.cid as cid0_1_, students0_.sid as sid1_, students0_.sid as sid1_0_, students0_.sname as sname1_0_, students0_.description as descript3_1_0_, students0_.cid as cid1_0_ from Student students0_ where students0_.cid=?
	 	
	 	会查询出所有班级，然后带着每一个cid去查找学生，不管班级有没有数据          	n+1查询
	 	
	 	
	 	fetch="subselect"
	 */
	
	@Test 
	public void testQueryClasses(){
		Session session=sessionFactory.openSession();
		List<Classes> classesList=session.createQuery("from Classes").list();
		for(Classes classes:classesList){
			Set<Student> students=classes.getStudents();
			for(Student student:students){
				System.out.println(student.getSname());
			}
		}
		
		session.close();
		
	}
	
	/**
	 * 查询cid为2,3,4,5,6的所有学生     fetch="subselect"
	 */
	@Test 
	public void testQueryClassesWhenCid(){
		Session session=sessionFactory.openSession();
		List<Classes> classesList=session.createQuery("from Classes where cid in(2,3,4,5,6)").list();
		for(Classes classes:classesList){
			Set<Student> students=classes.getStudents();
			for(Student student:students){
				System.out.println(student.getSname());
			}
		}
		
		session.close();
		
	}
	
	/**
	 * 查询cid为2的所有学生  fetch="join"   变成一条语句
	 * 	Hibernate: select classes0_.cid as cid0_1_, 
	 * 				classes0_.cname as cname0_1_, 
	 * 				classes0_.description as descript3_0_1_, 
	 * 				students1_.cid as cid0_3_, students1_.sid as sid3_,
	 * 				 students1_.sid as sid1_0_, students1_.sname as sname1_0_,
	 * 				 students1_.description as descript3_1_0_, 
	 * 				students1_.cid as cid1_0_ from Classes classes0_ left outer join Student students1_ on classes0_.cid=students1_.cid where classes0_.cid=?
	 */
	
	@Test 
	public void testQueryClassesByCid(){
		Session session=sessionFactory.openSession();
		Classes classes=(Classes)session.get(Classes.class, 2L);
		Set<Student> students=classes.getStudents();
		for(Student student:students){
			System.out.println(student.getSname());
		}
		
		session.close();
		
	}
}
