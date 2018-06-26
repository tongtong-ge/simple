package tk.mybatis.simple.mapper;

import java.util.List;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

public interface UserMapper {
	/**
	 * 通过 id 查询用户
	 * 
	 * @param 用户id
	 * @return 用户信息
	 */
	SysUser selectById(Long id);
	
	/**
	 * 查询全部用户
	 * 
	 * @return
	 */
	List<SysUser> selectAll();
	
	/**
	 * 根据用户id获取角色信息
	 * 
	 * @param 用户id
	 * @return 角色信息
	 */
	List<SysRole> selectRolesByUserId(Long userId);
}
