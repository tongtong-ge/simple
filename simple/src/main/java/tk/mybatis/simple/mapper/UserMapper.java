package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysUser;

public interface UserMapper {
	/**
	 * 通过 id 查询用户
	 * @param 用户id
	 * @return 用户信息
	 */
	SysUser selectById(Long id);
}
