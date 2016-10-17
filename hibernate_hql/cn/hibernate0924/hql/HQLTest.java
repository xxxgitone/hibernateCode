package cn.hibernate0924.hql;

import java.util.List;
import java.util.Set;

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
	
	/**
	 * 左外连接
	 * 
	 * 	#查询所有班级和其学生，不管有没有学生（左外连接）

		select c.*,s.*
		from classes c left join student s on(c.cid=s.cid)
	 */
	@Test
	public void testSelect_Classes_Student_left(){
		Session session = sessionFactory.openSession();
		//结构不怎么样
		List classesList = session.createQuery("from Classes c left outer join c.students s").list();
		System.out.println(classesList.size());
		session.close();
	}
	
	/**
	 * 迫切左外连接
	 * 
	 * 	#查询所有班级和其学生，不管有没有学生（左外连接）

		select c.*,s.*
		from classes c left join student s on(c.cid=s.cid)
	 */
	@Test
	public void testSelect_Classes_Student_Left_Fetch(){
		Session session = sessionFactory.openSession();
		//结构好
		List classesList = session.createQuery("from Classes c left outer join fetch c.students s").list();
		System.out.println(classesList.size());
		session.close();
	}
	
	/**
	 * 在页面上显示cname，sname
	 * 		*采用迫切内连接可以做，但是不好的一点是把数据库中两张表所有的数据都放到了内存中
	 * 		*带构造器函数的select查询和fetch不能共存,只能选择其中一个
	 */
	@Test
	//建一个bean  ClassesView
	public void testSelect_Constructor_Classes_Student_Inner_Fetch(){
		Session session = sessionFactory.openSession();
		//报错：
		//List classesList = session.createQuery("select new cn.hibernate0924.hql.ClassesView(c.cname,s.sname) from Classes c inner join fetch c.students s").list();
		
		List classesList = session.createQuery("select new cn.hibernate0924.hql.ClassesView(c.cname,s.sname) from Classes c inner join c.students s").list();
		
		System.out.println(classesList.size());
		session.close();
	}
	
	/**
	 * 查询所有班级的名称，并且把有学生的班级的所有学生名称也查询出来
	 */
	@Test
	public void testSelect_Constructor_Classes_Student_Left(){
		Session session = sessionFactory.openSession();
		
		List classesList = session.createQuery("select new cn.hibernate0924.hql.ClassesView(c.cname,s.sname) from Classes c left join c.students s").list();
		
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
	
	/**
	 * 等值连接
	 */
	@Test
	public void testSelect_inner(){
		Session session = sessionFactory.openSession();
		
		List classesList = session.createQuery("from Course c inner join c.students s").list();
		
		System.out.println(classesList.size());
		session.close();
	}
	
	
	@Test
	public void testCreateDB(){
		
	}
	
	
	/**
	 * 一对多和多对多
	 */
	
	/**
	 * 查询所有班级的所有学生，所有学生的所有课程
	 * select c.*,s.*,cc.*
			from classes c left join student s on(c.cid=s.cid)
             left join student_course sc on(s.sid=sc.sid)
             left join course cc on(cc.cid=sc.cid)
	 */
	@Test
	public void testQueryAll(){
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("from");
		stringBuffer.append(" Classes c left outer join fetch c.students s left outer join fetch s.courses cc ");
		Session session = sessionFactory.openSession();
		
		List<Classes> classesList = session.createQuery(stringBuffer.toString()).list();
		
		//如果list中有重复值，转换为set就可以去除
		for(Classes classes:classesList){
			Set<Student> students=classes.getStudents();
			for(Student student:students){
				Set<Course> courses=student.getCourses();
				for(Course course:courses){
					System.out.println(course.getCname());
				}	
			}
		}	
		
	
		session.close();
	}
	
	/**
	 * 	*  	页面上数据的字段和数据库中字段差不多
		    	这个时候，采用迫切连接  结构比较好
		*   如果页面上的字段很少，要按照需求加载数据，采用带构造函数的select查询

	 */
	
	
	
	/**
	 * 1.要求用迫切左外连接查询所有的学生，并且把又课程的学生的所有课程也查询出来
	 * 2.只需要查询cname，sname
	 * 3.采用内连接查询有课程的学生和有学生的课程
	 * 4.用迫切内连接把classes和student和course三张表的数据联系在一起，如果有重复的数据去除
	 */
	
	
	
}
