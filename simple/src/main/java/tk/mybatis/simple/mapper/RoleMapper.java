package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import tk.mybatis.simple.model.SysRole;

public interface RoleMapper {
	
	@Select({"select id, role_name roleName, enabled, create_by createBy, create_time createTime ",
		     "from sys_role where id = #{id}"})
	SysRole selectById(Long id);
	
	// 不需要返回主键
	@Insert({"insert into sys_role(role_name, enabled, create_by, create_time) ",
		"values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
	int insert(SysRole sysRole);
	
	// 返回自增主键
	@Insert({"insert into sys_role(role_name, enabled, create_by, create_time) ",
	"values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert2(SysRole sysRole);
	
	// 返回非自增主键
	@Insert({"insert into sys_role(role_name, enabled, create_by, create_time) ",
	"values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
	@SelectKey(statement = "SELECT LAST_INSERT_ID()",
			keyProperty = "id", resultType = Long.class, before = false)
	int insert3(SysRole sysRole);
	
	@Update({"update sys_role set role_name = #{roleName}, ",
		"enabled = #{enabled}, create_by = #{createBy}, ",
		"create_time = #{createTime, jdbcType=TIMESTAMP} ",
		"where id = #{id}"})
	int updateById(SysRole sysRole);
	
	@Delete({"delete from sys_role where id = #{id}"})
	int deleteById(Long id);
	
	/**
	 * 获取所有的角色和权限
	 * 
	 * @return
	 */
	List<SysRole> selectAllRoleAndPrivileges();
	
	/**
	 * 根据用户Id获取用户角色
	 * 
	 * @param userId
	 * @return
	 */
	SysRole selectRoleByUserId(Long userId);
	
	/**
	 * 根据用户Id获取用户的角色信息
	 * 
	 * @param userId
	 * @return
	 */
	List<SysRole> selectRoleByUserIdChoose(Long userId);
}
