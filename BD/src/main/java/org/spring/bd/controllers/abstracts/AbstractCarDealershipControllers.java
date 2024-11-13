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
import org.spring.bd.core.dto.AutomobileDTO;
import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractCarDealershipControllers {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ServiceInterface<CarDealershipDTO, Integer> carDealershipService;

    public AbstractCarDealershipControllers(ServiceInterface<CarDealershipDTO, Integer> carDealershipService) {
        this.carDealershipService = carDealershipService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Allows to get existing record by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the car dealership"
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
                    description = "Car dealership not found",
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
    public ResponseEntity<CarDealershipDTO> getCarDealership(@PathVariable Integer id) {
        logger.info("Sending automobile with id={}", id);

        CarDealershipDTO carDealershipDTO = carDealershipService.getRecordById(id);

        return ResponseEntity.status(HttpStatus.OK).body(carDealershipDTO);
    }

    @GetMapping("/all")
    @Operation(summary = "Get All", description = "Allows to get all existing records")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all car dealerships"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = GlobalExceptionHandler.ERROR_EXAMPLE)
                    )
            )
    })
    public ResponseEntity<List<CarDealershipDTO>> getAllCarDealerships() {
        logger.info("Sending all automobiles");

        List<CarDealershipDTO> carDealerships = carDealershipService.getAllRecords();

        return ResponseEntity.status(HttpStatus.OK).body(carDealerships);
    }

    @PostMapping
    @Operation(summary = "Save", description = "Allows to add/save new record")
    public ResponseEntity<CarDealershipDTO> saveCarDealership(@Valid @RequestBody CarDealershipDTO carDealership) {
        logger.info("Saving car dealership with name={}", carDealership.getName());

        CarDealershipDTO carDealershipDTO = carDealershipService.saveRecord(carDealership);

        return ResponseEntity.status(HttpStatus.CREATED).body(carDealershipDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Allows to delete existing record by its id")
    public ResponseEntity<Void> deleteCarDealership(@PathVariable Integer id) {
        logger.info("Deleting car dealership with id={}", id);

        carDealershipService.deleteRecordById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing records")
    public ResponseEntity<Void> deleteAllCategories() {
        logger.info("Deleting all car dealerships");

        carDealershipService.deleteAllRecords();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
