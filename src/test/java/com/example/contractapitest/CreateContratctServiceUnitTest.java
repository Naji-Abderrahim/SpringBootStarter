package com.example.contractapitest;

import java.time.LocalDate;
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
public class CreateContratctServiceUnitTest {

	@Mock
	private ContractRepository contractRepository;

	@InjectMocks
	private ContractServices contractService;

	@Test
	void createContractWithValidInfoTest() {

		Contract contract = new Contract(
				"testTitle",
				"TestClientName",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2023, 6, 1),
				ContractStatus.DRAFT);
		when(contractRepository.save(contract)).thenReturn(contract);

		Contract result = contractService.createContract(contract);

		assertEquals(contract, result);
	}

	@Test
	void createContractWithEmptyTitle() {

		String expectedError = "Title is Empty";
		Contract invalidContract = new Contract(
				"",
				"TestClientName",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2023, 6, 1),
				ContractStatus.DRAFT);

		Exception exception = assertThrows(
				IllegalStateException.class,
				() -> contractService.createContract(invalidContract));

		assertEquals(expectedError, exception.getMessage());
	}

	@Test
	void createContractWithEmptyClientName() {

		String expectedError = "Client Name is Empty";
		Contract invalidContract = new Contract(
				"testTitle",
				"",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2023, 6, 1),
				ContractStatus.DRAFT);

		Exception exception = assertThrows(
				IllegalStateException.class,
				() -> contractService.createContract(invalidContract));

		assertEquals(expectedError, exception.getMessage());
	}

}
