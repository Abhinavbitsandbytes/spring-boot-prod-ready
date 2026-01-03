package com.example.productioReady.productioReady.clients.impl;

import com.example.productioReady.productioReady.advice.ApiResponse;
import com.example.productioReady.productioReady.clients.EmployeeClient;
import com.example.productioReady.productioReady.dto.EmployeeDTO;
import com.example.productioReady.productioReady.exceptions.ResourceNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("trying to retrieve employee list in getAllEmployees");
        try {
           ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                   .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->{
                       log.error(new String(res.getBody().readAllBytes()));
                       // error is related to client i.e 3rd party service
                       throw new ResourceNotFoundExceptions("Could not create new Employee");
                   })
                    .body(new ParameterizedTypeReference<>() {
                    });
           log.debug("Succesfully retrieved employee list");
           // {} is used to denote code block in log message, we can pass variables after comma to be logged
           log.trace("Retrived employees list in getAllEmployees: {}", employeeDTOList.getData());
            return employeeDTOList.getData();
        } catch (Exception e) {
            log.error("exception occurred while retrieving employee list", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId){
        try {
            ApiResponse<EmployeeDTO> employeeDTO = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTO.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO){
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) ->{
                        // error is related to client i.e 3rd party service
                        throw new ResourceNotFoundExceptions("Could not create new Employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {});
            // employeeDTOApiResponse contains headers, status code and body
            // employeeDTOApiResponse.getBody() contains ApiResponse<EmployeeDTO>
            // employeeDTOApiResponse.getBody().getData() contains EmployeeDTO
            // employeeDTOApiResponse.getStatusCode() contains status code
            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

// instead of .body, we can also use .toEntity but in that case we have to extract body from entity.
// using entity we can also extract headers and status code if needed.
// Also, we can have custom error handling using .onStatus method.
//