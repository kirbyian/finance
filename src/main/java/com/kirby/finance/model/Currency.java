package com.kirby.finance.model;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Currency extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Currency(String name) {
		setName(name);
	}

}
