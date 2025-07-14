package com.example.contractapi;

import org.springframework.web.bind.annotation.*;

import java.net.HttpCookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/contracts")
public class ContractDeleteController {
    @Autowired
    private ContractServices contractService;

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContract(@PathVariable Long id) {
		try {
			contractService.deleteContract(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
		}
	}
}
