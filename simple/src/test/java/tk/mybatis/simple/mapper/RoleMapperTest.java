package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysRole;

public class RoleMapperTest extends BaseMapperTest {

	@Test
	public void testSelectAll() {
		//获取一个SqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			// 获取RoleMapper接口
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			// 调用selectById方法
			SysRole role = roleMapper.selectById(1L);
			// role不为空
			Assert.assertNotNull(role);
			// roleName=管理员
			Assert.assertEquals("管理员", role.getRoleName());
		} finally {
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	
}
