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
@Document("buyer")
public class MongoBuyer {
    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Field(name = "model_id")
    private Integer modelId;

    @Field(name = "email")
    private String email;

    @Field(name = "name")
    private String name;

    @Field(name = "phoneNumber")
    private String phoneNumber;
}
