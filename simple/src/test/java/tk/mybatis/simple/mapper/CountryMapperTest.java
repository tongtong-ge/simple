package tk.mybatis.simple.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import tk.mybatis.simple.model.Country;

public class CountryMapperTest {
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
	
	@Test
	public void testSelectAll() {
		//通过SqlSessionFactory工厂对象获取一个SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			//通过SqlSession的selectList方法查找到CountryMapper.xml中的id="selectAll"的方法，执行SQL查询
			List<Country> countryList = sqlSession.selectList("selectAll");
			printCountryList(countryList);
		} finally {
			//关闭sqlSession
			//否则会造成因为连接没有关闭导致数据库连接数过多，造成系统崩溃
			sqlSession.close();
		}
	}
	
	private void printCountryList(List<Country> countryList) {
		for (Country country : countryList) {
			System.out.printf("%-4d%4s%4s\n", 
					country.getId(), country.getCountryname(), country.getCountrycode());
		}
	}
}
