package cn.hibernate0924.onetomany.doubl;


import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class realse_rebuild_R {
	/**
	 * 9.解除该班级和所有学生之间的关系，在重新建立该班级和一些新的学员之间的关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		
		/**
		 * 1.获取班级
		 * 2.获取班级学生
		 * 3.遍历学生，吧学生班级设置为null
		 * 4.新建两个学院
		 * 5.建立两个学院的班级的关系
		 */
		Classes classes=(Classes)session.get(Classes.class, 1L);
		
		Set<Student> students=classes.getStudents();
		for(Student student:students){
			student.setClasses(null);
		}
		
		//session.delete(classes);  //删除班级
		
		Student student=new Student();
		student.setSname("王五");
		student.setDescription("nimei");
		
		Student student2=new Student();
		student2.setSname("王8");
		student2.setDescription("haha");
		
		student.setClasses(classes);
		student2.setClasses(classes);
		
		students.add(student);
		students.add(student2);
		
//		session.save(student);
//		session.save(student2);
		
		/**
		 * 当发生transaction.commit时，hibernate内部会检查所有持久化对象
		 *   会对持久化对象进行更新，因为classes是一个持久化状态对象，所以hibernate
		 *   内部要对classes进行更新，而classes.hbm.xml文件中有 <set name="students" cascade="save-update" inverse="true">
		 *   意味着在更新classes的时候，要级联操作student，而student是一个临时状态的对象
		 *   所以要对student进行保存操作，在保存的时候，就把外键更新了
		 */
		
		
		
		
		transaction.commit();
		session.close();
	}

}
