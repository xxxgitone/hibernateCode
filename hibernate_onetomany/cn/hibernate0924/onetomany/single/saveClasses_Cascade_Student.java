package cn.hibernate0924.onetomany.single;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveClasses_Cascade_Student {
	/**
	 * 保存班级的同时，级联保存学生
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
		
		//显示保存：需要执行session.save操作
		//隐式操作：不在做保存操作，而交给hibernate，需要设置cascade级联，在Classes配置文件中
		//session.save(student);
		
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
