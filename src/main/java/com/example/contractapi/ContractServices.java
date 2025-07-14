package com.example.contractapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;
import java.util.List;


@Service
public class ContractServices {

	@Autowired
	private ContractRepository contractRepo;

	private void validateContractInfo(Contract contract) {
		if (!contract.isDateCorrect()) {
			throw new IllegalStateException("Start Date is bigger then End Date");
		}
		if (contract.getTitle().isEmpty()) {
			throw new IllegalStateException("Title is Empty");
		}
		if (contract.getClientName().isEmpty()) {
			throw new IllegalStateException("Client Name is Empty");
		}
	}

	public Contract createContract(Contract contract) {
		validateContractInfo(contract);
		return contractRepo.save(contract);
	}

	public Contract updateContract(Long id, Contract newContract) {
		validateContractInfo(newContract);
		Optional<Contract> optionalContract = contractRepo.findById(id);
		if (optionalContract.isPresent()) {
			Contract existingContract = optionalContract.get();
			existingContract.setTitle(newContract.getTitle());
			existingContract.setClientName(newContract.getClientName());
			existingContract.setStartDate(newContract.getStartDate());
			existingContract.setEndDate(newContract.getEndDate());
			existingContract.setStatus(newContract.getStatus());
			return contractRepo.save(existingContract);
		}
		throw new EmptyResultDataAccessException("Given Id for this Contratc Does not exist", 1);
	}

	public List<Contract> getAllContractsByStatus(String contractState) {
		if (contractState == null)
			return contractRepo.findAll();
		ContractStatus contStatus = ContractStatus.valueOf(contractState.toUpperCase());
		List<Contract> contracts = contractRepo.findByStatus(contStatus);
		if (contracts.isEmpty())
			throw new EmptyResultDataAccessException("Contract does not exist" , 1);
		return contracts;
	}

	public Contract getContractByIdAndStatus(Long id, String contractState) {
		Optional<Contract> optContract;
		if (contractState == null)
			optContract = contractRepo.findById(id);
		else {
				ContractStatus contStatus = ContractStatus.valueOf(contractState.toUpperCase());
				optContract = contractRepo.findByIdAndStatus(id, contStatus);
			}
		if (optContract.isPresent()) {
			return optContract.get();
		} else {
			throw new EmptyResultDataAccessException("Contract does not exist" , 1);
		}
	}

	public void deleteContract(Long id) {
		if (!contractRepo.existsById(id)) {
			throw new EmptyResultDataAccessException("Contract does not exist", 1);
		}
		contractRepo.deleteById(id);
	}
}
