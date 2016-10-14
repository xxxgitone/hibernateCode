package cn.hibernate0924.manytomany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveStudent_R {
	/**
	 * 5.已经存在一个课程，新建一个学生，并且建立该学生和该课程之间的关系
	 * 		1.查找一个课程
	 * 		2.新建一个学生
	 * 		3.通过学生来维护课程与学生之间的关系
	 * 
	 * 			Hibernate: select course0_.cid as cid0_0_, course0_.cname as cname0_0_, course0_.description as descript3_0_0_ from Course course0_ where course0_.cid=?
				Hibernate: select max(sid) from Student
				Hibernate: insert into Student (sname, description, sid) values (?, ?, ?)
				Hibernate: insert into student_course (sid, cid) values (?, ?)
				该语句建立关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/manytomany/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Course course=(Course)session.get(Course.class, 2L);
		
		Student student=new Student();
		student.setSname("慕容");
		student.setDescription("不知道啥");
		
		//通过学生来维护课程与学生之间的关系
		Set<Course> courses=new HashSet<Course>();
		courses.add(course);
		
		student.setCourses(courses);
		session.save(student);
		transaction.commit();
		session.close();
	}

}
