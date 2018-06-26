package tk.mybatis.simple.mapper;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

public class BaseMapperTest {
	private static SqlSessionFactory sqlSessionFactory;
	
	@BeforeClass
	public static void init() {
		try {
			//通过Resources工具类将mybatis-config.xml配置文件读入Reader
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			//通过SqlSessionFactoryBuilder建造类使用Reader创建SqlSessionFactory工厂对象
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (IOException ignore) {
			ignore.printStackTrace();
		}
	}
	
	/**
	 * 通过SqlSessionFactory工厂对象获取一个SqlSession
	 * <br>使用后必须将SqlSession关闭，否则会造成因为连接没有关闭导致数据库连接数过多
	 * <br>造成系统崩溃
	 * @return SqlSession
	 */
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
