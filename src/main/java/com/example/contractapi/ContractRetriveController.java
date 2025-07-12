package com.example.contractapi;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

@RestController
@RequestMapping("/contracts")
public class ContractRetriveController {
	@Autowired
	private ContractRepository contractRepository;

	@GetMapping
	public List<Contract> getContracts(@RequestParam(required = false) String status) {
		if (status == null || status.isEmpty())
			return contractRepository.findAll();
		try {
			ContractStatus contStatus = ContractStatus.valueOf(status.toUpperCase());
			return contractRepository.findByStatus(contStatus);
		} catch (IllegalArgumentException e) {
			return Collections.emptyList();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contract> getContractById(
			@PathVariable Long id,
			@RequestParam(required = false) String status) {
		Optional<Contract> optContract;
		if (status == null || status.isEmpty())
			optContract = contractRepository.findById(id);
		else {
			try {
				ContractStatus contStatus = ContractStatus.valueOf(status.toUpperCase());
				optContract = contractRepository.findByIdAndStatus(id, contStatus);
			} catch (IllegalArgumentException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		if (optContract.isPresent()) {
			return new ResponseEntity<>(optContract.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
