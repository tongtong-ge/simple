package tk.mybatis.simple.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public class UserMapperTest extends BaseMapperTest {

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
			
			// 查询用户名和用户邮箱时
			query = new SysUser();
			query.setUserName("ad");
			query.setUserEmail("test@mybatis.com");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() == 0);
			
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateByIdSelective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个新的user对象
			SysUser user = new SysUser();
			// 更新
			user.setId(1L);
			// 修改邮箱
			user.setUserEmail("test@mybatis.com");
			// 返回值result是执行Sql影响的行数
			int result = userMapper.updateByIdSelective(user);
			Assert.assertEquals(1, result);
			// 查询更新后的数据
			user = userMapper.selectById(1L);
			// 修改后的用户名保持不对，但是邮箱变成新的
			Assert.assertEquals("admin", user.getUserName());
			Assert.assertEquals("test@mybatis.com", user.getUserEmail());
		} finally {
			// 数据回滚
			sqlSession.rollback();
			// 关闭sqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testInsert2Selective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个user对象
			SysUser user = new SysUser();
			user.setUserName("test-selective");
			user.setUserPassword("123456");
			user.setUserInfo("test info");
			user.setCreateTime(new Date());
			// 插入数据库中，并返回user的id
		    userMapper.insert2(user);
		    // 获取插入的这条数据
		    user = userMapper.selectById(user.getId());
		    Assert.assertEquals("test@mybatis.com", user.getUserEmail());
		} finally {
			sqlSession.rollback();
			sqlSession.close(); 
		}
	}
	
	@Test
	public void testSelectByIdOrUserName() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 查询用户id
			SysUser query = new SysUser();
			query.setId(1L);
			query.setUserName("admin");
			SysUser user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);
			// 当没有id时，查询用户名
			query.setId(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);
			// 当id和name都为空时
			query.setUserName(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNull(user);
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectByIdList() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<Long> idList = new ArrayList<Long>();
			idList.add(1L);
			idList.add(2L);
			
			// 业务逻辑中必须校验idList.size() > 0
			List<SysUser> userList = userMapper.selectByIdList(idList);
			Assert.assertEquals(2, userList.size());
			
		} finally {
			sqlSession.close();
		}
	}
	
	@Test
	public void testInsertList() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个user对象
			List<SysUser> userList = new ArrayList<SysUser>();
			for (int i = 0; i < 2; i++) {
				SysUser user = new SysUser();
				user.setUserName("test" + i);
				user.setUserPassword("123456");
				user.setUserEmail("test@mybatis.com");
				user.setCreateTime(new Date());
				
				userList.add(user);
			}
			
			// 将新建的对象批量插入数据库中
			int result = userMapper.insertList(userList);
			
			Assert.assertEquals(2, result);
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}
	
	@Test
	public void testUpdateByMap() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			// 查询条件， 同样也是更新字段，必须保证该值存在
			map.put("id", 1L);
			// 更新其他字段
			map.put("user_email", "test_admin@mybatis.com");
			map.put("user_password", "123456989");
			// 更新数据
			userMapper.updateByMap(map);
			// 根据当前id查询修改后的数据
			SysUser user = userMapper.selectById(1L);
			Assert.assertEquals("test_admin@mybatis.com", user.getUserEmail());
		} finally {
			sqlSession.rollback();
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	
}
