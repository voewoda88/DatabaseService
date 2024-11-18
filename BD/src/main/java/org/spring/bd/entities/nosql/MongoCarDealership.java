package org.spring.bd.entities.nosql;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("car_dealership")
public class MongoCarDealership {
    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Field(name = "model_id")
    private Integer modelId;

    @Field(name = "address")
    private String address;

    @Field(name = "email")
    private String email;

    @Field(name = "name")
    private String name;

    @Field(name = "phoneNumber")
    private String phoneNumber;

    @Field(name = "workingTime")
    private String workingTime;

    @Field(name = "automobileIds")
    private Set<Integer> automobiles = new HashSet<>();
}
