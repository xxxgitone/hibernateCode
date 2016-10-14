package util;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class sessionFactory {
	private static SessionFactory sessionFactory=null;
	static {
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/onetomany/doubl/hibernate.cfg.xml");
		
		sessionFactory=configuration.buildSessionFactory();
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
}
