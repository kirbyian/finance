package com.kirby.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirby.finance.model.Saving;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
	
	List<Saving> findByUserName(String username);

}
