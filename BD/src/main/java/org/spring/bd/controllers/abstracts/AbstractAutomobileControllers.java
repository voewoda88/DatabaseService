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
import org.spring.bd.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractAutomobileControllers {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ServiceInterface<AutomobileDTO, Integer> automobileService;

    public AbstractAutomobileControllers(ServiceInterface<AutomobileDTO, Integer> automobileService) {
        this.automobileService = automobileService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Allows to get existing record by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the automobile"
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
                    description = "Automobile not found",
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
    public ResponseEntity<AutomobileDTO> getAutomobile(@PathVariable Integer id) {
        logger.info("Sending automobile with id={}", id);

        AutomobileDTO automobileDTO = automobileService.getRecordById(id);

        return ResponseEntity.status(HttpStatus.OK).body(automobileDTO);
    }

    @GetMapping("/all")
    @Operation(summary = "Get All", description = "Allows to get all existing records")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all automobiles"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = GlobalExceptionHandler.ERROR_EXAMPLE)
                    )
            )
    })
    public ResponseEntity<List<AutomobileDTO>> getAllAutomobiles() {
        logger.info("Sending all automobiles");

        List<AutomobileDTO> automobiles = automobileService.getAllRecords();

        return ResponseEntity.status(HttpStatus.OK).body(automobiles);
    }

    @PostMapping
    @Operation(summary = "Save", description = "Allows to add/save new record")
    public ResponseEntity<AutomobileDTO> saveAutomobile(@Valid @RequestBody AutomobileDTO automobile) {
        logger.info("Saving automobile with model={} and brand={}", automobile.getModel(), automobile.getBrand());

        AutomobileDTO automobileDTO = automobileService.saveRecord(automobile);

        return ResponseEntity.status(HttpStatus.CREATED).body(automobileDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Allows to delete existing record by its id")
    public ResponseEntity<Void> deleteAutomobile(@PathVariable Integer id) {
        logger.info("Deleting automobile with id={}", id);

        automobileService.deleteRecordById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing records")
    public ResponseEntity<Void> deleteAllCategories() {
        logger.info("Deleting all automobiles");

        automobileService.deleteAllRecords();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
