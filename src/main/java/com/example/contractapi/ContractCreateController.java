package com.example.contractapi;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/contracts")
public class ContractCreateController {
    @Autowired
    private ContractServices contractService;

    @PostMapping
    public ResponseEntity<?> createContract(@RequestBody Contract contract) {
		try {
			Contract cont = contractService.createContract(contract);
			return new ResponseEntity<>(cont, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error: " + e.getMessage());
		}

    }

	@PutMapping("/{id}")
	public ResponseEntity<?> updateContract(
			@PathVariable Long id,
			@RequestBody Contract newContract) {
			try {
				Contract cont = contractService.updateContract(id, newContract);
				return new ResponseEntity<>(cont, HttpStatus.NO_CONTENT);
			} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error: " + e.getMessage());
			}
	}
}
