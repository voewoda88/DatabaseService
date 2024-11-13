package org.spring.bd.controllers.abstracts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.bd.configuration.GlobalExceptionHandler;
import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractEmployeeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ServiceInterface<EmployeeDTO, Integer> employeeService;

    public AbstractEmployeeController(ServiceInterface<EmployeeDTO, Integer> employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Allows to get existing employee record by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the employee"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request body",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = GlobalExceptionHandler.ERROR_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = GlobalExceptionHandler.ERROR_EXAMPLE)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = GlobalExceptionHandler.ERROR_EXAMPLE)
                    )
            )
    })
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Integer id) {
        logger.info("Sending employee with id={}", id);

        EmployeeDTO employeeDTO = employeeService.getRecordById(id);

        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    @GetMapping("/all")
    @Operation(summary = "Get All", description = "Allows to get all existing employee records")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all employees"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = GlobalExceptionHandler.ERROR_EXAMPLE)
                    )
            )
    })
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        logger.info("Sending all employees");

        List<EmployeeDTO> employees = employeeService.getAllRecords();

        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @PostMapping
    @Operation(summary = "Save", description = "Allows to add/save new employee record")
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employee) {
        logger.info("Saving employee with name={}", employee.getName());

        EmployeeDTO employeeDTO = employeeService.saveRecord(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Allows to delete existing employee record by its id")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        logger.info("Deleting employee with id={}", id);

        employeeService.deleteRecordById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing employee records")
    public ResponseEntity<Void> deleteAllEmployees() {
        logger.info("Deleting all employees");

        employeeService.deleteAllRecords();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
