package com.example.productioReady.productioReady;

import com.example.productioReady.productioReady.clients.EmployeeClient;
import com.example.productioReady.productioReady.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductioReadyApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;
	@Test
	void getAllEmployees() {
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	// Here we are calling the employee client to fetch all employees and print them out.
	// steps -
	// 1. Create 1 employee by running the Employee Service application
	//	     - uri - http://localhost:8080/employees
	//		 - method - POST
	//		 - body - {"name": "Abhinav",
	//    "email": "abhi@gmail.com",
	//    "age": 20,
	//    "dateOfJoining": "2025-08-18",
	//    "isActive": true,
	//    "role": "USER",
	//    "salary": 5000.00
	//}
	// this will create 1 employee in the Employee Service database
	// 2. Now run this test case to fetch all employees from Employee Service using EmployeeClient

	// Expected Output -
//	[EmployeeDTO(id=1, name=Abhinav, email=abhi@gmail.com, age=20, role=USER, salary=5000.0, dateOfJoining=2025-08-18, isActive=null)]

	// it means we have successfully fetched the employee data from Employee Service using EmployeeClient (third party service)





}
