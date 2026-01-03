package com.example.productioReady.productioReady;

import com.example.productioReady.productioReady.clients.EmployeeClient;
import com.example.productioReady.productioReady.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductioReadyApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;
	@Test
	@Order(3)
	void getAllEmployees() {
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeById(){
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	@Order(1)
	void createNewEmployee() {
		EmployeeDTO newEmployeeDTO = new EmployeeDTO(null, "Abhinav", "Abhinav@gmail.com", 20, "USER", 5000.0, LocalDate.of(2020, 12, 1), true);
		;
		EmployeeDTO createdEmployee = employeeClient.createNewEmployee(newEmployeeDTO);
		System.out.println(createdEmployee);

	}
}
