package org.spring.bd.entities.nosql;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("employee")
public class MongoEmployee {
    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Field(name = "model_id")
    private Integer modelId;

    @Field(name = "name")
    private String name;

    @Field(name = "position")
    private String position;

    @Field(name = "salary")
    private Integer salary;

    @Field(name = "carDealershipId")
    private Integer carDealershipId;
}
