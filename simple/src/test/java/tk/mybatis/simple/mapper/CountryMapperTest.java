package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.CountryExample;

public class CountryMapperTest extends BaseMapperTest {
	/**
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
	
	@Test
	public void testExample() {
		// 获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			// 获取CountryMapper接口
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			// 创建Example对象
			CountryExample example = new CountryExample();
			// 设置排序规则  名字正序排列
			example.setOrderByClause("convert(countryname using gbk) asc");
			// 设置是否distinct去重
			example.setDistinct(true);
			// 创建条件
			CountryExample.Criteria criteria = example.createCriteria();
			// id >= 1
			criteria.andIdGreaterThanOrEqualTo(1);
			// id < 4
			criteria.andIdLessThan(4);
			// countrycode like '%U%'
			// 注意like必须自己写上通配符的位置
			criteria.andCountrycodeLike("%U%");
			// or的情况
			CountryExample.Criteria or = example.or();
			// countryname=中国
			or.andCountrynameEqualTo("中国");
			// 执行查询
			List<Country> countryList = countryMapper.selectByExample(example);
			printCountryList(countryList);
		} finally {
			sqlSession.close();
		}
	}
	
	*/
	
	@Test
	public void testUpdateByExampleSelective() {
		SqlSession sqlSession = getSqlSession();
		try {
			// 获取CountryMapper接口
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			// 创建Example对象
			CountryExample example = new CountryExample();
			// 创建条件，只能有一个createCriteria
			CountryExample.Criteria criteria = example.createCriteria();
			// 更新所有id > 2的国家
			criteria.andIdGreaterThan(2);
			// 创建一个要设置的对象
			Country country = new Country();
			// 将国家名字设置为China
			country.setCountryname("China");
			// 执行查询
			countryMapper.updateByExampleSelective(country, example);
			// 输出
			printCountryList(countryMapper.selectByExample(example));
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testDeleteByExample() {
		SqlSession sqlSession = getSqlSession();
		try {
			CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
			CountryExample example = new CountryExample();
			CountryExample.Criteria criteria = example.createCriteria();
			// 删除id>2的国家
			criteria.andIdGreaterThan(2);
			// 执行
			countryMapper.deleteByExample(example);
			
			Assert.assertEquals(0, countryMapper.countByExample(example));
		} finally {
			sqlSession.rollback();
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
