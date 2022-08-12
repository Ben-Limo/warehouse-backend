package com.teckmils.warehousemanagementsystem;

import com.teckmils.warehousemanagementsystem.domain.transaction.model.Transaction;
import com.teckmils.warehousemanagementsystem.domain.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class WarehouseManagementSystemApplicationTests {
	@Autowired
	private TransactionRepository transactionRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void findByCreatedAtBetweenMethod() {
		//start date
		Timestamp startDate = Timestamp.valueOf(
				LocalDateTime.of(
				LocalDate.of(2022, 8, 01),
				LocalTime.of(00,00,00))
		);

		//end date
		Timestamp endDate = Timestamp.valueOf(
				LocalDateTime.of(
						LocalDate.of(2022, 8, 05),
						LocalTime.of(00,00,00))
		);

		List<Transaction> transactions = transactionRepository.findByCreatedAtBetween(startDate, endDate);
		transactions.forEach(transaction -> System.out.println(transaction.getId()));
	}

}
