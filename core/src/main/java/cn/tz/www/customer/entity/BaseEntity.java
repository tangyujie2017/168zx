package cn.tz.www.customer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public abstract class BaseEntity  implements Serializable{


	
	
	

	/**
	 * 可用性
	 * 默认为true
	 */
	@Column(name = "enable")
	protected Boolean enable = true;



	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	



}
