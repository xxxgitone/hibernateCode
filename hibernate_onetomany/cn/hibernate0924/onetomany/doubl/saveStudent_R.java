package cn.hibernate0924.onetomany.doubl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class saveStudent_R {
	/**
	 * 5.已经存在一个班级，新建一个学生，并且建立该学生和该班级之间的关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		
		Classes classes=(Classes)session.get(Classes.class, 1L);
		Student student=new Student();
		student.setSname("小黄人");
		student.setDescription("臭不要脸");
		
		student.setClasses(classes);
		
		session.save(student);
		
		transaction.commit();
		session.close();
	}

}
