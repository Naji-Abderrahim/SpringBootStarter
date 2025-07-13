package com.example.contractapi;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractRetriveController {
	@Autowired
	private ContractServices contractService;

	@GetMapping
	public ResponseEntity<?> getContracts(@RequestParam(required = false) String status) {
		try {
			List<Contract> contracts = contractService.getAllContractsByStatus(status);
			return new ResponseEntity<>(contracts, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getContractById(
			@PathVariable Long id,
			@RequestParam(required = false) String status) {
		try {
			Contract contract = contractService.getContractByIdAndStatus(id, status);
			return new ResponseEntity<>(contract, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
		}
	}
}
