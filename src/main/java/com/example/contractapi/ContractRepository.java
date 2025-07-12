package com.example.contractapi;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface ContractRepository extends JpaRepository<Contract, Long> {
	List<Contract> findByStatus(ContractStatus status);
	Optional<Contract> findByIdAndStatus(Long id, ContractStatus status);
}

