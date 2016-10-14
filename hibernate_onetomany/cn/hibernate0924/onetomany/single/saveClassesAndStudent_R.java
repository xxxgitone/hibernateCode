package cn.hibernate0924.onetomany.single;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveClassesAndStudent_R {
	/**
	 * 4.保存班级的时候保存学生，并且建立班级和学生之间的关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/onetomany/single/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();

		Student student=new Student();
		student.setSname("班密");
		student.setDescription("班长旁边的，女士优先");
		
		//设置自inverse,在Classes配置文件中set
		//inverse:是用来维护关系的，默认值false维护  ，可不写         true表示不维护
//			*要么是一对多
//			*要么是多对多
//			*inverse所在映射文件对应的持久化对象来维护关系
		
		
		Set<Student> students=new HashSet<Student>();
		students.add(student);
		
		Classes classes=new Classes();
		classes.setCname("0909java极品班");
		classes.setDescription("还是极品号");
		
		classes.setStudents(students);
		
		session.save(classes);
		
		transaction.commit();
		session.close();
	}

}
