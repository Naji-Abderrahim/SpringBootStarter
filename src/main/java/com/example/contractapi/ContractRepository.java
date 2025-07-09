package com.example.contractapi;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ContractRepository extends JpaRepository<Contract, Long> {
	List<Contract> findByStatus(String status);
}

