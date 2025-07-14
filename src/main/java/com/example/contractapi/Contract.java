package com.example.contractapi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String clientName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	private ContractStatus status;

	public Contract(){
	}

	// this Constructor for test only
	// public Contract(Long id, String title, String clientName, LocalDate sDate, LocalDate edate, ContractStatus state) {
	// 	this.id = id;
	// 	this.title = title;
	// 	this.clientName = clientName;
	// 	this.startDate = sDate;
	// 	this.endDate = edate;
	// 	this.status = state;
	// }

	public Contract(String title, String clientName, LocalDate sDate, LocalDate edate, ContractStatus state) {
		// this.id = id;
		this.title = title;
		this.clientName = clientName;
		this.startDate = sDate;
		this.endDate = edate;
		this.status = state;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String tlte) {
		title = tlte;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String name) {
		clientName = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate newStartDate) {
		startDate = newStartDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate newEndDate) {
		endDate = newEndDate;
	}

	public ContractStatus getStatus() {
		return status;
	}

	public void setStatus(ContractStatus newStatus) {
		status = newStatus;
	}

	@JsonIgnore
	public Boolean isDateCorrect() {
		return startDate.isBefore(endDate);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Contract contract = (Contract) o;
		return Objects.equals(id, contract.id) &&
				Objects.equals(title, contract.title) &&
				Objects.equals(clientName, contract.clientName) &&
				Objects.equals(startDate, contract.startDate) &&
				Objects.equals(endDate, contract.endDate) &&
				Objects.equals(status, contract.status);
	}
}
