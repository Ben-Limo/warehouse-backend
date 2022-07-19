package com.teckmils.warehousemanagementsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseManagementSystemApplication {
	private static final Logger logger =
			LoggerFactory.getLogger(WarehouseManagementSystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WarehouseManagementSystemApplication.class, args);
		logger.info("Application Started");
	}

}
