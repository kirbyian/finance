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

}
