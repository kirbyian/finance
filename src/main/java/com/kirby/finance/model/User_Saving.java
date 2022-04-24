package com.kirby.finance.model;

import javax.persistence.Entity;

@Entity
public class User_Saving extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1257775805852555475L;
	private Long user_id;
	private Long saving_id;
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getSaving_id() {
		return saving_id;
	}
	public void setSaving_id(Long saving_id) {
		this.saving_id = saving_id;
	}

}
