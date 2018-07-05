package tk.mybatis.simple.model;

import java.sql.Date;
import java.util.List;

import tk.mybatis.simple.type.Enabled;

public class SysRole {
	private Long id;
	private String roleName;
	
	/**
	 * 有效标识
	 */
	private Enabled enabled;
	private Long createBy;
	private Date createTime;
	
	/**
	 * 创建信息
	 */
	/*private CreateInfo createInfo;*/
	
	/**
	 * 用户信息
	 */
	private SysUser user;
	
	/**
	 * 角色包含的权限列表
	 */
	List<SysPrivilege> privilegeList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Enabled getEnabled() {
		return enabled;
	}
	public void setEnabled(Enabled enabled) {
		this.enabled = enabled;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/*public CreateInfo getCreateInfo() {
		return createInfo;
	}
	public void setCreateInfo(CreateInfo createInfo) {
		this.createInfo = createInfo;
	}*/
	
	public List<SysPrivilege> getPrivilegeList() {
		return privilegeList;
	}
	public void setPrivilegeList(List<SysPrivilege> privilegeList) {
		this.privilegeList = privilegeList;
	}
}
