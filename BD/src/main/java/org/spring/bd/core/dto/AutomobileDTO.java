package org.spring.bd.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Schema(description = "Entry to save/get/update/delete a record in \"automobiles\" table")
public class AutomobileDTO {
    @NotNull
    @Schema(description = "Unique identifier of the automobile", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[A-Za-z -]+$", message = "Brand can only contain letters, spaces, and hyphens")
    @Schema(description = "Brand of the automobile", example = "Toyota")
    private String brand;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[A-Za-z0-9 -]+$", message = "Model can only contain letters, numbers, spaces, and hyphens")
    @Schema(description = "Model of the automobile", example = "Corolla")
    private String model;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[A-Za-z0-9 -]+$", message = "Generation can only contain letters, numbers, spaces, and hyphens")
    @Schema(description = "Generation of the automobile", example = "XSE")
    private String generation;

    @NotNull
    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    @Max(value = 2100, message = "Year must be less than or equal to 2100")
    @Schema(description = "Manufacturing year of the automobile", example = "2020")
    private Integer year;

    @NotNull
    @Positive(message = "Price must be greater than 0")
    @Schema(description = "Price of the automobile in currency units", example = "25000")
    private Integer price;

    @DecimalMin(value = "0.0", inclusive = false, message = "Engine volume must be greater than 0")
    @DecimalMax(value = "16.0", inclusive = false, message = "Engine volume must be less than 16.0")
    @Schema(description = "Engine volume in liters", example = "2.5")
    private BigDecimal engineVolume;

    @NotNull
    @Schema(description = "Type of gearbox", example = "automatic")
    private String gearbox;

    @NotNull
    @Schema(description = "Type of car body", example = "sedan")
    private String body;

    @NotNull
    @Schema(description = "Type of engine", example = "gasoline")
    private String engine;

    @NotNull
    @Schema(description = "Type of drive", example = "all-wheel drive")
    private String drive;

    @NotNull
    @Positive(message = "Power must be greater than 0")
    @Schema(description = "Power of the automobile's engine in horsepower", example = "200")
    private Integer power;

    @Schema(description = "Set of car dealership IDs associated with this automobile")
    private Set<Integer> carDealershipIds;
}
