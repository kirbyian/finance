package com.kirby.finance.model;

import javax.persistence.Entity;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

@Entity
public class Saving extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6381112364509575484L;
	private String description;
	@NotNull
	private String type;
	@NotNull
	@Min(value = 1)
	private Double goal;
	private Double amount;
	@NotNull
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Saving() {
		
	}

	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "saving_id", referencedColumnName = "id")
	//private List<User_Saving> user_savings;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getGoal() {
		return goal;
	}

	public void setGoal(Double goal) {
		this.goal = goal;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
