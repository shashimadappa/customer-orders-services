package com.example.demoCostumer;

import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demoCostumer.Repo.Repository;
import com.example.demoCostumer.Service.service;
import com.example.demoCostumer.model.customer;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DemoCostumerApplicationTests {

	@Autowired
	service service;
	@Autowired
	Repository repository;

	@Test
	void contextLoads() {
	}
	// @Test
	// public void testing(){
	// 	customer cus = new customer(1001, "shashi", "gold", 1000, 100, 11);
	// 	repository.save(cus);
	// 	Optional<customer> actualResult = repository.findRefundAmountByOrderId(11);
	// 	assertThat(actualResult);
	// }


}
