package org.spring.bd.entities.nosql;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("last_model_id")
public class LastModelId {
    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Field(name = "last_automobile_model_id")
    private Integer automobileModelId;

    @Field(name = "last_car_dealership_model_id")
    private Integer carDealershipModelId;

    @Field(name = "last_employee_model_id")
    private Integer employeeModelId;

    @Field(name = "last_buyer_model_id")
    private Integer buyerModelId;

    @Field(name = "last_order_model_id")
    private Integer orderModelId;
}
