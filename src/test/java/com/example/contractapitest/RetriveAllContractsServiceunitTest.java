package com.example.contractapitest;

import java.lang.module.ModuleDescriptor.Exports;
import java.time.LocalDate;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.contractapi.*;

@ExtendWith(MockitoExtension.class)
@Nested
public class RetriveAllContractsServiceunitTest {

	@Mock
	private ContractRepository contractRepository;

	@InjectMocks
	private ContractServices contractService;

	@Test
	void getAllContractsWhenDBIsEmptyTest() {

		List<Contract> contracts = List.of();

		when(contractRepository.findAll()).thenReturn(contracts);

		List<Contract> result = contractService.getAllContractsByStatus(null);

		assertEquals(contracts, result);
	}

	@Test
	void getAllContractsTest() {

		Contract contract1 = new Contract(
				"testTitle 1",
				"TestClientName 1",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2023, 6, 1),
				ContractStatus.DRAFT);
		Contract contract2 = new Contract(
				"testTitle 2",
				"TestClientName 2",
				LocalDate.of(2021, 6, 1),
				LocalDate.of(2025, 7, 16),
				ContractStatus.ACTIVE);
		List<Contract> contracts = List.of(contract1, contract2);

		when(contractRepository.findAll()).thenReturn(contracts);

		List<Contract> result = contractService.getAllContractsByStatus(null);

		assertEquals(contracts, result);
	}

	@Test
	void getAllContractsWithStatusTest() {

		Contract contract1 = new Contract(
				"testTitle 1",
				"TestClientName 1",
				LocalDate.of(2021, 6, 1),
				LocalDate.of(2025, 7, 16),
				ContractStatus.ACTIVE);
		Contract contract2 = new Contract(
				"testTitle 2",
				"TestClientName 2",
				LocalDate.of(2022, 6, 1),
				LocalDate.of(2025, 9, 22),
				ContractStatus.ACTIVE);
		List<Contract> expectedContracts = List.of(contract1, contract2);
		ContractStatus state = ContractStatus.ACTIVE;

		when(contractRepository.findByStatus(state)).thenReturn(expectedContracts);

		List<Contract> result = contractService.getAllContractsByStatus("Active");

		assertEquals(expectedContracts, result);
	}

	@Test
	void getAllContractsWithInvalidStatusTest() {

		assertThrows(
				IllegalArgumentException.class,
				() -> contractService.getAllContractsByStatus("TEST"));

	}
}
