package cn.hibernate0924.onetomany.single;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class RealseAll_Rebulid_R {
	/**
	 * 9.解除该班级和所有学生之间的关系，在重新建立该班级和一些新的学员之间的关系
	 * 		
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		
		Classes classes=(Classes)session.get(Classes.class, 5L);
		
		List<Student> students=session.createQuery("from Student where sid in(3,4,5)").list();
		//重新建立班级和学生之间的联系
		classes.setStudents(new HashSet<Student>(students));
		
		transaction.commit();
		session.close();
}

}
