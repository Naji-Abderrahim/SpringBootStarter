package com.example.contractapitest;

import java.lang.module.ModuleDescriptor.Exports;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.contractapi.*;

@ExtendWith(MockitoExtension.class)
@Nested
public class RetriveContractByIDServiceUnitTest {

	@Mock
	private ContractRepository contractRepository;

	@InjectMocks
	private ContractServices contractService;

	@Test
	void getContractWhenDBIsEmptyTest() {

		assertThrows(
				EmptyResultDataAccessException.class,
				() -> contractService.getContractByIdAndStatus(1L, null));
		// Contract contract = new Contract();
		//
		// when(contractRepository.findById(1L).get()).thenReturn(contract);
		//
		// Contract result = contractService.getContractByIdAndStatus(1L, null);
		//
		// assertEquals(contract, result);
	}

	@Test
	void getContractByIdTest() {

		Contract contract = new Contract(
				1L,
				"testTitle 1",
				"TestClientName 1",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2023, 6, 1),
				ContractStatus.DRAFT);

		when(contractRepository.findById(1L)).thenReturn(Optional.of(contract));

		Contract result = contractService.getContractByIdAndStatus(1L, null);

		assertEquals(contract, result);
	}

	@Test
	void getContractByIdWithStatusTest() {

		Contract contract = new Contract(
				1L,
				"testTitle 1",
				"TestClientName 1",
				LocalDate.of(2021, 6, 1),
				LocalDate.of(2025, 7, 16),
				ContractStatus.ACTIVE);
		ContractStatus state = ContractStatus.ACTIVE;

		when(contractRepository.findByIdAndStatus(1L, state)).thenReturn(Optional.of(contract));

		Contract result = contractService.getContractByIdAndStatus(1L, "Active");

		assertEquals(contract, result);
	}
}
