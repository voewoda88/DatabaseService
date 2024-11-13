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
import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.core.dto.OrderDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractOrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ServiceInterface<OrderDTO, Integer> orderService;

    public AbstractOrderController(ServiceInterface<OrderDTO, Integer> orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get", description = "Allows to get an existing order by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the order"
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
                    description = "Order not found",
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
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Integer id) {
        logger.info("Sending order with id={}", id);

        OrderDTO orderDTO = orderService.getRecordById(id);

        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @GetMapping("/all")
    @Operation(summary = "Get All", description = "Allows to get all existing orders")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved all orders"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = GlobalExceptionHandler.ERROR_EXAMPLE)
                    )
            )
    })
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        logger.info("Sending all orders");

        List<OrderDTO> orders = orderService.getAllRecords();

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping
    @Operation(summary = "Save", description = "Allows to add/save a new order")
    public ResponseEntity<OrderDTO> saveOrder(@Valid @RequestBody OrderDTO order) {
        logger.info("Saving order with final value={} and status={}", order.getFinalValue(), order.getStatus());

        OrderDTO orderDTO = orderService.saveRecord(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete", description = "Allows to delete an existing order by its ID")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        logger.info("Deleting order with id={}", id);

        orderService.deleteRecordById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all", description = "Allows to delete all existing orders")
    public ResponseEntity<Void> deleteAllOrders() {
        logger.info("Deleting all orders");

        orderService.deleteAllRecords();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
