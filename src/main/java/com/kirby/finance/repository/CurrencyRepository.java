package com.kirby.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirby.finance.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	

}
