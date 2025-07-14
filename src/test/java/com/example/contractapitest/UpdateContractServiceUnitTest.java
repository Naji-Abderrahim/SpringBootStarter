package com.example.contractapitest;

import java.time.LocalDate;
import java.util.Optional;

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
public class UpdateContractServiceUnitTest {

	@Mock
	private ContractRepository contractRepository;

	@InjectMocks
	private ContractServices contractService;

	@Test
	void updateContractWithValidInfoTest() {

		Contract oldContract = new Contract(
				"oldTitle",
				"oldClientName",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2023, 6, 1),
				ContractStatus.DRAFT);
		Contract newContract = new Contract(
				"newTitle",
				"newClientName",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2023, 6, 1),
				ContractStatus.ACTIVE);

		when(contractRepository.findById(1L)).thenReturn(Optional.of(oldContract));
		when(contractRepository.save(oldContract)).thenReturn(newContract);

		Contract result = contractService.updateContract(1L, newContract);

		assertEquals(newContract, result);
	}

	@Test
	void updateContractWithEmptyTitle() {

		String expectedError = "Title is Empty";
		Contract invalidContract = new Contract(
				"",
				"TestClientName",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2023, 6, 1),
				ContractStatus.DRAFT);

		Exception exception = assertThrows(
				IllegalStateException.class,
				() -> contractService.updateContract(1L, invalidContract));

		assertEquals(expectedError, exception.getMessage());
	}

	@Test
	void updateContractWithInvalidDate() {

		String expectedError = "Start Date is bigger then End Date";
		Contract invalidContract = new Contract(
				"TestTiltle",
				"TestClientName",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2019, 6, 1),
				ContractStatus.DRAFT);

		Exception exception = assertThrows(
				IllegalStateException.class,
				() -> contractService.updateContract(1L, invalidContract));

		assertEquals(expectedError, exception.getMessage());
	}

	@Test
	void UpdateContractWithEmptyClientName() {

		String expectedError = "Client Name is Empty";
		Contract invalidContract = new Contract(
				"testTitle",
				"",
				LocalDate.of(2020, 6, 1),
				LocalDate.of(2023, 6, 1),
				ContractStatus.DRAFT);

		Exception exception = assertThrows(
				IllegalStateException.class,
				() -> contractService.updateContract(1L, invalidContract));

		assertEquals(expectedError, exception.getMessage());
	}

	@Test
	void updateContractWithNull() {

		Contract invalidContract = null;

		assertThrows(
				NullPointerException.class,
				() -> contractService.updateContract(1L, invalidContract));
	}
}
