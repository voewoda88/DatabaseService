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
import org.spring.bd.core.dto.BuyerDTO;
import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractBuyerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ServiceInterface<BuyerDTO, Integer> buyerService;

    public AbstractBuyerController(ServiceInterface<BuyerDTO, Integer> buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Allows to get existing buyer record by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the buyer"
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
                    description = "Buyer not found",
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
    public ResponseEntity<BuyerDTO> getBuyer(@PathVariable Integer id) {
        logger.info("Sending buyer with id={}", id);

        BuyerDTO buyerDTO = buyerService.getRecordById(id);

        return ResponseEntity.status(HttpStatus.OK).body(buyerDTO);
    }

    @GetMapping("/all")
    @Operation(summary = "Get All", description = "Allows to get all existing buyer records")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all buyers"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = GlobalExceptionHandler.ERROR_EXAMPLE)
                    )
            )
    })
    public ResponseEntity<List<BuyerDTO>> getAllBuyers() {
        logger.info("Sending all buyers");

        List<BuyerDTO> buyers = buyerService.getAllRecords();

        return ResponseEntity.status(HttpStatus.OK).body(buyers);
    }

    @PostMapping
    @Operation(summary = "Save", description = "Allows to add/save new buyer record")
    public ResponseEntity<BuyerDTO> saveBuyer(@Valid @RequestBody BuyerDTO buyer) {
        logger.info("Saving buyer with name={}", buyer.getName());

        BuyerDTO buyerDTO = buyerService.saveRecord(buyer);

        return ResponseEntity.status(HttpStatus.CREATED).body(buyerDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Allows to delete existing buyer record by its ID")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Integer id) {
        logger.info("Deleting buyer with id={}", id);

        buyerService.deleteRecordById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing buyer records")
    public ResponseEntity<Void> deleteAllBuyers() {
        logger.info("Deleting all buyers");

        buyerService.deleteAllRecords();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
