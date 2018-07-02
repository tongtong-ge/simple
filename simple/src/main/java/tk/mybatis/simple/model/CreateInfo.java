package tk.mybatis.simple.model;

import java.util.Date;

/**
 * 创建信息
 * 
 * @author tongtong.ge
 *
 */
public class CreateInfo {
	/**
	 * 创建人
	 */
	private Long createBy;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateDate(Date createTime) {
		this.createTime = createTime;
	}
	
}
