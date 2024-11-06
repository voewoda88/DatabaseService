package org.spring.bd.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Builder
@EqualsAndHashCode
@Schema(description = "Entry to get/update/delete a record in \"car dealership\" table")
public class CarDealershipDTO {
    @NotNull
    @Schema(description = "Unique identifier of the car dealership", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^[A-Za-z0-9 .,-]+$", message = "Name must match the specified pattern")
    @Schema(description = "Name of the car dealership")
    private String name;

    @NotNull
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-z0-9 .,-]+$", message = "Address must match the specified pattern")
    @Schema(description = "Address of the car dealership")
    private String address;

    @NotNull
    @Size(max = 15)
    @Pattern(regexp = "^(\\+375(29|44|25|33))\\d{7}$", message = "Phone number must match the specified pattern")
    @Schema(description = "Phone number of the car dealership")
    private String phoneNumber;

    @NotNull
    @Email
    @Size(max = 255)
    @Schema(description = "Email of the car dealership")
    private String email;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])-(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$",
            message = "Working time must match the specified pattern")
    @Schema(description = "Working time of the car dealership")
    private String workingTime;

    @Schema(description = "Set of automobile IDs associated with this car dealership")
    private Set<Integer> automobileIds;

}
