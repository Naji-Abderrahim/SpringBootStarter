package com.example.contractapitest;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.contractapi.*;

@ExtendWith(MockitoExtension.class)
@Nested
public class DeleteContractServiceUnitTest {

	@Mock
	private ContractRepository contractRepository;

	@InjectMocks
	private ContractServices contractService;

	@Test
	void deleteContractWhenContractIsNotFound() {

		String expectedMessage = "Contract does not exist";

		when(contractRepository.existsById(1L)).thenReturn(false);

		Exception result = assertThrows(
				EmptyResultDataAccessException.class,
				() -> contractService.deleteContract(1L));

		assertEquals(expectedMessage, result.getMessage());
	}

	@Test
	void deleteContractWhenIdIsNull() {

		when(contractRepository.existsById(null)).thenThrow(new IllegalArgumentException(""));
		assertThrows(
				IllegalArgumentException.class,
				() -> contractService.deleteContract(null));
	}

	@Test
	void deleteContractInValidConditions() {

		Long id = 1L;
		when(contractRepository.existsById(id)).thenReturn(true);
		assertDoesNotThrow(() -> contractService.deleteContract(1L));

		verify(contractRepository, times(1)).deleteById(id);
	}
}
