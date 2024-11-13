package org.spring.bd.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entry to save/get/update/delete a record in \"order\" table")
public class OrderDTO {
    @Schema(description = "Unique identifier of the order", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "Final value of the order", example = "15000.00")
    private BigDecimal finalValue;

    @Schema(description = "Created date of the order", example = "2023-09-15")
    private LocalDate createdDate;

    @Schema(description = "Status of the order", example = "open")
    private String status;

    @Schema(description = "ID of the associated automobile")
    private Integer automobileId;

    @Schema(description = "ID of the associated car dealership")
    private Integer carDealershipId;

    @Schema(description = "ID of the associated employer")
    private Integer employerId;

    @Schema(description = "ID of the associated buyer")
    private Integer buyerId;
}
