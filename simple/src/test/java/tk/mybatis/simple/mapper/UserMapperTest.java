package tk.mybatis.simple.mapper;

import java.util.List;

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
}
