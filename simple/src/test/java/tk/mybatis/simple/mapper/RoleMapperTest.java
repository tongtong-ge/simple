package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;

public class RoleMapperTest extends BaseMapperTest {
	/**
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
	
	@Test
	public void testSelectRoleByUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectRoleByUserId(1L);
			Assert.assertNotNull(role);
			List<SysPrivilege> privilegeList = role.getPrivilegeList();
			Assert.assertNotNull(privilegeList);
		} finally {
			sqlSession.close();
		}
	}
	*/
	
	@Test
	public void testSelectRoleByUserIdChoose() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectById(2L);
			role.setEnabled(0);
			roleMapper.updateById(role);
			
			List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
			for (SysRole r : roleList) {
				System.out.println("角色名：" + r.getRoleName());
				if(r.getId().equals(1L)) {
					Assert.assertNotNull(r.getPrivilegeList());
				} else if(r.getId().equals(2L)) {
					Assert.assertNull(r.getPrivilegeList());
					continue;
				}
				
				for (SysPrivilege privilege : r.getPrivilegeList()) {
					System.out.println("权限名：" + privilege.getPrivilegeName());
				}
			}
		} finally {
			sqlSession.close();
		}
	}
	
}
