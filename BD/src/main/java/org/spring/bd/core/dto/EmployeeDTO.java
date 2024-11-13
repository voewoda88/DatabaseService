package org.spring.bd.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entry to save/get/update/delete a record in \"employee\" table")
public class EmployeeDTO {
    @Schema(description = "Unique identifier of the employee", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "Name of the employee", example = "John Doe")
    private String name;

    @Schema(description = "Position of the employee", example = "Manager")
    private String position;

    @Schema(description = "Salary of the employee in currency units", example = "50000.00")
    private BigDecimal salary;

    @Schema(description = "ID of the associated car dealership")
    private Integer carDealershipId;
}
