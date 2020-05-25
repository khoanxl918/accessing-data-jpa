package com.example.accessingdatajpa;

import com.example.accessingdatajpa.model.Customer;
import com.example.accessingdatajpa.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {
	public static final Logger logger = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	/**
	 * CommandLineRunner bean automatically runs the code when the application launches.
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			//save a few customers
			repository.save(new Customer("Khoa", "Nguyen"));
			repository.save(new Customer("Hoang", "Le"));
			repository.save(new Customer("Linh", "Bui"));
			repository.save(new Customer("Kien", "Dinh"));

			//fetch all customers
			logger.info("Customers found with findAll()");
			logger.info("------------------------------");
			for (Customer customer : repository.findAll()) {
				logger.info(customer.toString());
			}
			logger.info("");

			//fetch by id
			logger.info("Customer found with findById(3L)");
			logger.info("--------------------------------");
			Customer customer = repository.findById(3L);
			logger.info(customer.toString());
			logger.info("");

			//fetch by last name
			logger.info("Customer found with findByLastName(\"Nguyen\")");
			logger.info("----------------------------------------------");
			repository.findByLastName("Nguyen").forEach(nguyen -> logger.info(nguyen.toString()));
			logger.info("");
		};
	}
}
