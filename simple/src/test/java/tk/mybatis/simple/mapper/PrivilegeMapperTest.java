package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysPrivilege;

public class PrivilegeMapperTest extends BaseMapperTest {
	/**
	@Test
	public void testSelectById() {
		SqlSession sqlSession = getSqlSession();
		try {
			PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
			SysPrivilege privilege = privilegeMapper.selectById(1L);
			Assert.assertNotNull(privilege);
			Assert.assertEquals("用户管理", privilege.getPrivilegeName());
		} finally {
			// 关闭sqlSession
			sqlSession.close();
		}
	}
	*/
	
	@Test
	public void testSelectPrivilegeByRoleId() {
		SqlSession sqlSession = getSqlSession();
		try {
			PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
			List<SysPrivilege> privilegeList = privilegeMapper.selectPrivilegeByRoleId(1L);
			Assert.assertNotNull(privilegeList);
		} finally {
			sqlSession.close();
		}
	}
}
