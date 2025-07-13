package com.example.contractapi;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/contracts")
public class ContractDeleteController {
    @Autowired
    private ContractRepository contractRepository;

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
		if (contractRepository.existsById(id)) {
			contractRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
