package cn.hibernate0924.sessionFactory;

/**
 * 1.sessionFactory中存放着配置文件和所有的映射文件 2.是一个重量级别的类 3.一个数据库只有一个sessionFactory
 * 4.一个配置文件只能连接一个数据库 5.只要创建了sessionFactory，表就创建完成 6.存放的数据是共享的，但这个类本身是线程安全的
 * 7.sessionFactory实际上是接口，实现类是sessionFactoryImpl
 * 
 * @author Administrator
 * 
 */
public class sessionFactoryTest {

}
