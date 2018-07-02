package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysPrivilege;
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
	
	@Test
	public void testSelectAllRoleAndPrivileges() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
			System.out.println("角色数：" + roleList.size());
			for (SysRole role : roleList) {
				System.out.println("角色名：" + role.getRoleName());
				for (SysPrivilege privilege : role.getPrivilegeList()) {
					System.out.println("权限名：" + privilege.getPrivilegeName());
				}
			}
		} finally {
			sqlSession.close();
		}
	}
}
