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
@Document("order")
public class MongoOrder {
    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Field(name = "model_id")
    private Integer modelId;

    @Field(name = "status")
    private String status;

    @Field(name = "createdDate")
    private String createdDate;

    @Field(name = "finalValue")
    private Integer finalValue;

    @Field(name = "automobileId")
    private Integer automobileId;

    @Field(name = "buyerId")
    private Integer buyerId;

    @Field(name = "employeeId")
    private Integer employerId;

    @Field(name = "carDealershipId")
    private Integer carDealershipId;
}
