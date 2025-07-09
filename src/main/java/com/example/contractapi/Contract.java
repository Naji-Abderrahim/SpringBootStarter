package com.example.contractapi;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


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


	public Contract() {
    }

	public Long getId(){
		return id;
	}
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String tlte){
		title = tlte;
	}
	
	public String getClientName(){
		return clientName;
	}
	public void setClientName(String name){
		clientName = name;
	}

	public LocalDate getStartDate(){
		return startDate;
	}
	public void setStartDate(LocalDate newStartDate){
		startDate = newStartDate;
	}

	public LocalDate getEndDate(){
		return endDate;
	}
	public void setEndDate(LocalDate newEndDate){
		endDate = newEndDate;
	}

	public ContractStatus getStatus(){
		return status;
	}
	public void setStatus(ContractStatus newStatus){
		status = newStatus;
	}

	@JsonIgnore
	public Boolean isDateCorrect(){
		return startDate.isBefore(endDate);
	}
}

