package cn.hibernate0924.onetomany.doubl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class saveClasses_Cascade_S {
	/**
	 * 保存班级的时候同时保存学生
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Classes classes=new Classes();
		classes.setCname("fhf");
		classes.setDescription("dsgkhsd");
		
		Student student=new Student();
		student.setSname("haha");
		student.setDescription("fdslkgkdsh");
		
		//Set<Student> students=new HashSet<Student>();
		//students.add(student);
		
		//通过classes建立与student的联系
//		Hibernate: select max(cid) from Classes
//			Hibernate: select max(sid) from Student
//			Hibernate: insert into Classes (cname, description, cid) values (?, ?, ?)
//			Hibernate: insert into Student (sname, description, cid, sid) values (?, ?, ?, ?)
//			Hibernate: update Student set cid=? where sid=?
		 
		//classes.setStudents(students);
		
		
		//通过students建立与classes的联系
//		Hibernate: select max(sid) from Student
//		Hibernate: select max(cid) from Classes
//		Hibernate: insert into Classes (cname, description, cid) values (?, ?, ?)
//		Hibernate: insert into Student (sname, description, cid, sid) values (?, ?, ?, ?)
		//student.setClasses(classes);  
		//对student的增删改本身就是对外建的操作，所以这里不再发出update语句
		//一对多多的一方维护关系好
		student.setClasses(classes);
		
		//session.save(classes);
		session.save(student);
		transaction.commit();
		session.close();
		
		
	}

}
