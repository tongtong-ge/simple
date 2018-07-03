package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.PrivilegeProvider;

public interface PrivilegeMapper {
	@SelectProvider(type = PrivilegeProvider.class, method = "selectById")
	SysPrivilege selectById(Long id);
	
	/**
	 * 根据角色Id查询相关权限信息
	 * 
	 * @param roleId
	 * @return
	 */
	List<SysPrivilege> selectPrivilegeByRoleId(Long roleId);
}
