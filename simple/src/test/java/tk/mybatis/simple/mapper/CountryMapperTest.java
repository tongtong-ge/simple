package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import tk.mybatis.simple.model.Country;

public class CountryMapperTest extends BaseMapperTest {
	/*private static SqlSessionFactory sqlSessionFactory;*/
	
	/*@BeforeClass
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
	}*/
	
	@Test
	public void testSelectAll() {
		//获取一个SqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//通过SqlSession的selectList方法查找到CountryMapper.xml中的id="selectAll"的方法，执行SQL查询
			//此时使用的是公共Mapper测试库，所有selectAll不在是唯一的了，调用时必须带上命名空间（namespace）
			List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
			printCountryList(countryList);
		} finally {
			//关闭sqlSession
			//否则会造成因为连接没有关闭导致数据库连接数过多，造成系统崩溃
			sqlSession.close();
		}
	}
	
	/**
	 * 循序打印结果集
	 * @param countryList
	 */
	private void printCountryList(List<Country> countryList) {
		for (Country country : countryList) {
			System.out.printf("%-4d%4s%4s\n", 
					country.getId(), country.getCountryname(), country.getCountrycode());
		}
	}
}
