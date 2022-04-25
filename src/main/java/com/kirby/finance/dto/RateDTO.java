package com.kirby.finance.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RateDTO {

	private LocalDateTime currentTime;
	private String name;
	private double value;
	private String fiat;
	private double allTimeHigh;
	private double priceChange24hr;
	private double marketCap;
	private double maxSupply;
	
	public RateDTO(LocalDateTime currentTime, String name, double value) {
		this.currentTime = currentTime;
		this.name = name;
		this.value = value;
	}
	
	

}
