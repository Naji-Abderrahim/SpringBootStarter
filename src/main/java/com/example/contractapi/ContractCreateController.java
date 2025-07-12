package com.example.contractapi;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/contracts")
public class ContractCreateController {
    @Autowired
    private ContractRepository contractRepository;

    @PostMapping
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
		if (!contract.isDateCorrect()) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		if (contract.getTitle().isEmpty() || contract.getClientName().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		contractRepository.save(contract);
		return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }


	@PutMapping("/{id}")
	public ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody Contract newContract) {
		Optional<Contract> optionalContract = contractRepository.findById(id);
		if (optionalContract.isPresent()) {
			Contract existingContract = optionalContract.get();
			existingContract.setTitle(newContract.getTitle());
			existingContract.setClientName(newContract.getClientName());
			existingContract.setStartDate(newContract.getStartDate());
			existingContract.setEndDate(newContract.getEndDate());
			existingContract.setStatus(newContract.getStatus());
			if (!existingContract.isDateCorrect()){
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			if (existingContract.getTitle().isEmpty() || existingContract.getClientName().isEmpty()){
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}

			contractRepository.save(existingContract);
			return new ResponseEntity<>(existingContract, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
