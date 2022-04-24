package com.kirby.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirby.finance.model.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
	

}
