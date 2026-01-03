package com.example.productioReady.productioReady.clients;

import com.example.productioReady.productioReady.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();
}
