#hibernate学习
##1.第一个hibernate例子
* 需要引入的jar包

	![hibernate jar](https://github.com/xxxgitone/learningProcess/blob/master/Other/image/hibernate_jar.png "Markdown")
* 配置文件 hibernate.cfg.xml(一般放在src下，也可不放在，但在后面加载配置文件的时候要写上完整路径)
		
		<!DOCTYPE hibernate-configuration PUBLIC
			"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
			"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
		
		<hibernate-configuration>
		<session-factory name="foo">
		
			<!-- 数据库的连接配置 -->
			<!-- 数据驱动 -->
			<property name="connection.driver_class">
				com.mysql.jdbc.Driver
			</property>
			<!--  <property name="hibernate.connection.driver_class">
				org.gjt.mm.mysql.Driver
			</property>-->
		
			<!-- 数据库的url
			Hibernate.connection.url  表示要链接的数据库地址
			Hibernate.connection.username     要连接的数据库的用户名
			Hibernate.connection.password      要连接的数据库的密码

			-->
			
			<property name="hibernate.connection.url">
				jdbc:mysql://127.0.0.1/hibernate0924?useUnicode=true&amp;characterEncoding=UTF-8
			</property>
			<property name="hibernate.connection.username">root</property>
			<property name="hibernate.connection.password">123456</property>
		
		
			<!-- 数据库的方言-->
			<property name="hibernate.dialect">org.hibernate.dialect.MySQLInnoDBDialect</property>
		
			<!-- 辅助配置，显示SQL语句-->
			<property name="hibernate.show_sql">true</property>
		
			<!-- hibernate针对建表的操作
				update 检查，没有创建
			 -->
			<property name="hbm2ddl.auto">update</property> 
		
			<!-- 数据库的连接配置 ,可以在Configuration中添加-->
			
			<mapping resource="cn/hibernate0924/domain/Person.hbm.xml" />
		
		</session-factory>
		</hibernate-configuration>

* 持久化类

		public class Person implements Serializable {
			private Long pid; // 标识属性
			private String pname;
			private String psex;
			// public Person(){

			// }
		
			// public Person(Long pid,String pname){ //单这样写会出错，必须要有一个默认的构造函数
			// this.pid=pid;
			// this.pname=pname;
			// }
			//get和set方法省略
		}

* Person.hbm.xml文件(放在对应持久化类的包下)

		<?xml version="1.0"?>
		<!DOCTYPE hibernate-mapping PUBLIC
		      "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
		          "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
		
		<hibernate-mapping>
				<!-- 描述持久化类,name位类的全名，table为表明，可以不写，默认跟类名一样 -->
				
		        <class name="cn.hibernate0924.domain.Person" table="person">
		        
		        	<!-- 主键，name为属性的名称 -->
		        	
		        	<id name="pid" column="pid" type="java.lang.Long">
		        		<!-- 主键产生器 
		        			1.increment 先查询该表中的主键最大值，然后将最大值加1；效率较低，主键值连续
		        			2.identity  根据底层数据库的自增生成，效率高，但主键不连贯
		        			3.assigned 手动赋值
		        		-->
		        		
		        		<generator class="increment" />
		        	</id>
		        	
		        	<!-- 字段名 -->
		        	<property name="pname" column="pname" type="string"/>
		        	
		        	<property name="psex" column="psex" type="string"/>
		        	     	
		        </class>    
		
		</hibernate-mapping>

* 客户端

	* 保存

			public static void main(String[] args) {
					// TODO Auto-generated method stub
					Person person = new Person();
					// person.setPid(9L);
					person.setPname("wang");
					person.setPsex("女");
					System.out.println(person.toString());
			
					Configuration configuration = new Configuration();
					// 加载配置文件
					configuration.configure("hibernate.cfg.xml");
					// 采用工厂模式创建sessionFactory
			
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
			
					Transaction transaction = session.beginTransaction();
			
					session.save(person);
			
					// 提交事务
					transaction.commit();
			
					session.close();
				}

	* 删除

			public static void main(String[] args) {
					// TODO Auto-generated method stub
					Configuration configuration = new Configuration();
					// 加载配置文件
					configuration.configure("hibernate.cfg.xml");
					// 采用工厂模式创建sessionFactory
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
			
					Transaction transaction = session.beginTransaction();
					/**
					 * 1.推荐
					 */
					Person person = (Person) session.get(Person.class, 1L);
					/**
					 * 2.
					 */
					// Person person=new Person();
					// person.setPid(2L);
			
					session.delete(person);
			
					// 提交事务
					transaction.commit();
			
					session.close();
				}

	* 更新

			public static void main(String[] args) {
					// TODO Auto-generated method stub
			
					Configuration configuration = new Configuration();
					// 加载配置文件
					configuration.configure("hibernate.cfg.xml");
					// 采用工厂模式创建sessionFactory
					SessionFactory sessionFactory = configuration.buildSessionFactory();
					Session session = sessionFactory.openSession();
			
					Transaction transaction = session.beginTransaction();
			
					/**
					 * 1.先把修改的那行提取出来 Serializable 是String和包装类型的父类
					 * session.get调用的是该对象默认的构造函数创建的对象，所以一个持久化类中必须包含一个默认的构造函数
					 */
					Person person = (Person) session.get(Person.class, 2L);
					System.out.println(person);
					person.setPsex("不详");
					/**
					 * 
					 * 在执行session.update的时候，hibernate内部会会为person对象去对照内存快照（副本），如果有属性发生变化
					 * 才要执行update语句，如果不改变，则不发出update语句
					 */
			
					// session.update(person); //可以不写,因为从数据库提取出来，已经是持久化状态
			
					/**
					 * 2.新创建一个person对象 把pid为1的放进去 设置修改 其他没有设置的字段会为空，因为是新的对象，不推荐使用
					 */
					// Person person1=new Person();
					// person1.setPid(1L);
					// person1.setPsex("女");
					// session.update(person1);
			
					// 提交事务
					transaction.commit();
			
					session.close();
				}

	* 查询

			public static void main(String[] args) {
					// TODO Auto-generated method stub
					Configuration configuration = new Configuration();
					// 加载配置文件
					configuration.configure("hibernate.cfg.xml");
					// 采用工厂模式创建sessionFactory
					SessionFactory sessionFactory = configuration.buildSessionFactory();
			
					Session session = sessionFactory.openSession();
			
					List<Person> personList = session.createQuery("from Person").list();
			
					System.out.print(personList.size());
			
				}

##2.常用的主键生成机制

|标识符生成器|描述|
|-----------|---|
|Increment|由hibernate自动以递增的方式生成表识符，每次增量为1|
|Identity|由底层数据库生成表识符。条件是数据库支持自动增长数据类型|
|Native|根据底层数据库对自动生成表示符的能力来选择identity、sequence、hilo|
|assigned|适用于自然主键。由java程序负责生成标识符。不能把setID()方法声明为|
|hilo|使用一个高/低位算法生成的long、short或int类型的标识符|

####2.1	increment 标识符生成器
*	increment 标识符生成器由 Hibernate 以递增的方式为代理主键赋值
*	Hibernate 会先读取 NEWS 表中的主键的最大值, 而接下来向 NEWS 表中插入记录时, 就在 max(id) 的基础上递增, 增量为 1.(带走+1)
*	适用范围:

	*	由于 increment 生存标识符机制不依赖于底层数据库系统, 因此它适合所有的数据库系统
	*	适用于只有单个 Hibernate 应用进程访问同一个数据库的场合 
	*	OID 必须为 long, int 或 short 类型, 如果把 OID 定义为 byte 类型, 在运行时会抛出异常
####2.2	identity 标识符生成器
*	identity 标识符生成器由底层数据库来负责生成标识符, 它要求底层数据库把主键定义为自动增长字段类型（加1带走）
*	适用范围:

	*	由于 identity 生成标识符的机制依赖于底层数据库系统, 因此, 要求底层数据库系统必须支持自动增长字段类型. 支持自动增长字段类型的数据库包括: DB2, Mysql, MSSQLServer, Sybase 等
	*	OID 必须为 long, int 或 short 类型, 如果把 OID 定义为 byte 类型, 在运行时会抛出异常

####2.3	native 标识符生成器
*	native 标识符生成器依据底层数据库对自动生成标识符的支持能力, 来选择使用 identity, sequence 或 hilo 标识符生成器. 
*	适用范围:
	*	由于 native 能根据底层数据库系统的类型, 自动选择合适的标识符生成器, 因此很适合于跨数据库平台开发
	*	OID 必须为 long, int 或 short 类型, 如果把 OID 定义为 byte 类型, 在运行时会抛出异常

####2.4	assigned 标识符生成器
*	hibernate和底层数据库都不帮助你生成主键，也就是说得自己在程序中手动的设置主键的值。
*	适用范围
	*	主键有一定的含义，需要根据业务产生的情况。

####2.5 Hilo 标识符生成器
*	使用一个高/低位算法生成的long、short或int类型的标识符，给定一个表和字段作为高位值的来源，默认的表是hibernate_unique_key，默认的字段是next_hi。它将id的产生源分成两部分，DB+内存，然后按照算法结合在一起产生id值，可以在很少的连接次数内产生多条记录，提高效率
*	使用hilo生成策略，要在数据库中建立一张额外的表，默认表名为hibernate_unique_key,默认字段为integer类型，名称是next_hi

##3.持久化对象的状态
###3.1	临时状态
*	在使用代理主键的情况下, OID 通常为 null
*	不处于 Session 的缓存中
*	在数据库中没有对应的记录
*	在以下情况在，java对象进入临时状态
	*	当通过new语句刚创建了一个新的对象，它处于临时状态，此时不和数据库中的任何记录对应

###3.2	持久化态对象
*	OID 不为 null
*	位于 Session 缓存中
*	持久化对象和数据库中的相关记录对应
*	Session 在清理缓存时, 会根据持久化对象的属性变化, 来同步更新数据库
*	在同一个 Session 实例的缓存中, 数据库表中的每条记录只对应唯一的持久化对象
*	sessiond的许多方法都可以触发java对象进入持久化状态
	*	








