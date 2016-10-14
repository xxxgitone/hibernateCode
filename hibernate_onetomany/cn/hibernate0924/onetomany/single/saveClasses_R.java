package cn.hibernate0924.onetomany.single;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import util.sessionFactory;

public class saveClasses_R {
	/**
	 * 6.已经存在一个学生，新建一个班级，并且建立该学生和该班级之间的关系
	 * 		1.查找学生
	 * 		2.新建班级
	 * 		3.建立关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/onetomany/single/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Student student=(Student)session.get(Student.class, 2L);
		
		Classes classes=new Classes();
		classes.setCname("辣眼极品班");
		classes.setDescription("你就是极品");
		
		Set<Student> students=new HashSet<Student>();
		students.add(student);
		
		classes.setStudents(students);
		session.save(classes);
		
		transaction.commit();
		session.close();
		
		
	}

}
