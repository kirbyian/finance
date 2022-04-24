package com.kirby.finance.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.sun.istack.NotNull;

import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4197354628212979220L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;

}