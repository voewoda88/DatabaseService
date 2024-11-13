package org.spring.bd.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Schema(description = "Entry to save/get/update/delete a record in \"buyer\" table")
public class BuyerDTO {
    @Schema(description = "Unique identifier of the buyer", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "Name of the buyer", example = "Alice Smith")
    private String name;

    @Schema(description = "Email of the buyer", example = "alice.smith@example.com")
    private String email;

    @Schema(description = "Phone number of the buyer", example = "+375291234567")
    private String phoneNumber;
}
