package com.example.contractapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ContractApiApplication {

	public static void main(String[] args) {
        SpringApplication.run(ContractApiApplication.class, args);
    }
}
