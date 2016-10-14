package cn.hibernate0924.onetomany.doubl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class updateStudent_Cascade {
	/**
	 * 6.已经存在一个学生，新建一个班级，并且建立该学生和该班级之间的关系
	 * 		1.因为存在建立关系的操作，所以操作学生端比较高
	 * 		2.在这里存在保存班级的操作，所以应该是通过跟更新学生级联班级
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		
		Student student=(Student)session.get(Student.class, 2L);
		Classes classes=new Classes();
		classes.setCname("传智播客集团");
		classes.setDescription("都是高管");
		
		student.setClasses(classes);
		session.save(classes);
		
		transaction.commit();
		session.close();
	}

}
