package com.example.contractapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;

    @PostMapping
    public ResponseEntity createContract(@RequestBody Contract contract) {
		if (!contract.isDateCorrect()) {
			return new ResponseEntity<>(
					new String("Starting date is bigger then Ending date!"),
					HttpStatus.FORBIDDEN
					);
		}
		if (contract.getTitle().length() == 0 || contract.getClientName().length() == 0) {
			return new ResponseEntity<>(
					new String("Title or Client Name is Empty!"),
					HttpStatus.FORBIDDEN
					);
		}
		contractRepository.save(contract);
		return new ResponseEntity<>(new String("New Contract created"), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Contract> getContract() {
        return contractRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {
		Optional<Contract> optContract = contractRepository.findById(id);
		if (optContract.isPresent()) {
			return new ResponseEntity<>(optContract.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

	@PutMapping("/{id}")
	public ResponseEntity<String> updateContract(@PathVariable Long id, @RequestBody Contract newContract) {
		Optional<Contract> optionalContract = contractRepository.findById(id);
		if (optionalContract.isPresent()) {
			Contract existingContract = optionalContract.get();
			existingContract.setTitle(newContract.getTitle());
			existingContract.setClientName(newContract.getClientName());
			existingContract.setStartDate(newContract.getStartDate());
			existingContract.setEndDate(newContract.getEndDate());
			if (!existingContract.isDateCorrect()){
				return new ResponseEntity<>(
						new String("Starting date is bigger then Ending date!"),
						HttpStatus.FORBIDDEN
						);
			}
			if (existingContract.getTitle().length() == 0 || existingContract.getClientName().length() == 0){
				return new ResponseEntity<>(
						new String("Title or Client Name is Empty!"),
						HttpStatus.FORBIDDEN
						);
			}

			contractRepository.save(existingContract);
			return new ResponseEntity<>(new String(""), HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(new String("Contract Not Found!"), HttpStatus.NOT_FOUND);
		}
	}

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

