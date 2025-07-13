package com.example.contractapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
	List<Contract> findByStatus(ContractStatus status);

	Optional<Contract> findByIdAndStatus(Long id, ContractStatus status);
}
