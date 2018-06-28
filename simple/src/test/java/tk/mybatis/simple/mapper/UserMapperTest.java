package tk.mybatis.simple.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class UserMapperTest extends BaseMapperTest {
	/********
	@Test
	public void testSelectById() {
		// 获取SqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			// 获取UserMapper接口
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 调用selectById方法
			SysUser user = userMapper.selectById(1L);
			// user不为空
			Assert.assertNotNull(user);
			// userName = admin
			Assert.assertEquals("admin", user.getUserName());
		} finally {
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 调用selectAll方法
			List<SysUser> userList = userMapper.selectAll();
			// 结果不为空
			Assert.assertNotNull(userList);
			// 用户数量大于0个
			Assert.assertTrue(userList.size() > 0);
		} finally {
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRolesByUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 调用selectRolesByUserId方法
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			// 结果不为空
			Assert.assertNotNull(roleList);
			// 用户角色数量大于0
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个user对象
			SysUser user = new SysUser();
			user.setUserName("dandy");
			user.setUserPassword("123456");
			user.setUserEmail("dandy@mybatis.com");
			user.setUserInfo("普通用户");
			// 正常情况下应该读入一张图片存到byte数组中
			user.setHeadImg(new byte[]{1, 2, 3});
			user.setCreateTime(new Date());
			// 将新建的对象插入数据库中，特别注意这里的返回值result是执行的Sql影响的行数
			int result = userMapper.insert(user);
			// 只插入1条数据
			Assert.assertEquals(1, result);
			// id为null，没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		} finally {
			// 为了不影响测试，这里选择回滚
			// 由于默认的sqlSessionFactory.openSession()是不自动提交的
			// 因此不手动执行commit也不会提交到数据库
			sqlSession.rollback();
			// 关闭SqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsert2() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个user对象
			SysUser user = new SysUser();
			user.setUserName("dandy");
			user.setUserPassword("123456");
			user.setUserEmail("dandy@mybatis.com");
			user.setUserInfo("普通用户");
			user.setHeadImg(new byte[]{1, 2, 3});
			user.setCreateTime(new Date());
			int result = userMapper.insert2(user);
			// 只插入1条数据
			Assert.assertEquals(1, result);
			// 因为id回写，所以id不为null
			Assert.assertNotNull(user.getId());
		} finally {
			sqlSession.rollback();
			// 关闭SqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testInsert3() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个user对象
			SysUser user = new SysUser();
			user.setUserName("dandy");
			user.setUserPassword("123456");
			user.setUserEmail("dandy@mybatis.com");
			user.setUserInfo("普通用户");
			user.setHeadImg(new byte[]{1, 2, 3});
			user.setCreateTime(new Date());
			int result = userMapper.insert3(user);
			// 只插入1条数据
			Assert.assertEquals(1, result);
			// 因为id回写，所以id不为null
			Assert.assertNotNull(user.getId());
		} finally {
			sqlSession.rollback();
			// 关闭SqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 从数据库查询1个user对象
			SysUser user = userMapper.selectById(1L);
			// 当前userName为admin
			Assert.assertEquals("admin", user.getUserName());
			// 修改用户名
			user.setUserName("admin_test");
			// 修改邮箱
			user.setUserEmail("admin_test@mybatis.com");
			// 更新数据，返回的result是执行Sql影响的行数
			int result = userMapper.updateById(user);
			// 只更新1条数据
			Assert.assertEquals(1, result);
			// 根据当前id查询修改后的数据
			user = userMapper.selectById(1L);
			// 修改后的名字是admin_test
			Assert.assertEquals("admin_test", user.getUserName());
		} finally {
			sqlSession.rollback();
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	*/
	
	/**
	@Test
	public void testDeleteById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 从数据库查询1个user对象，根据id=1查询
			SysUser user = userMapper.selectById(1L);
			// 现在还能查询出user对象
			Assert.assertNotNull(user);
			// 删除数据，返回的result是执行Sql影响的行数
			int result = userMapper.deleteById(1L);
			// 调用方法删除
			Assert.assertEquals(1, result);
			// 再次查询，这时应该没有值，为null
			user = userMapper.selectById(1L);
			Assert.assertNull(user);
			
			SysUser user2 = userMapper.selectById(2L);
			Assert.assertNotNull(user2);
			Assert.assertEquals(1, userMapper.deleteById(user2));
			Assert.assertNull(userMapper.selectById(2L));
		} finally {
			sqlSession.rollback();
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectRoleByUserIdAndRoleEnabled() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
			Assert.assertNotNull(roleList);
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	*/
	
	@Test
	public void testSelectByUser() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 只查询用户名时
			SysUser query = new SysUser();
			query.setUserName("ad");
			List<SysUser> userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			
			// 只查询用户邮箱时
			query = new SysUser();
			query.setUserEmail("test@mybatis.com");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);
			
			// 只查询用户邮箱时
			query = new SysUser();
			query.setUserName("ad");
			query.setUserEmail("test@mybatis.com");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() == 0);
			
		} finally {
			sqlSession.close();
		}
	}
}
