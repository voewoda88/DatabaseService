package org.spring.bd.entities.nosql;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("automobiles")
public class MongoAutomobile {
    @Id
    @Field(name = "_id")
    private ObjectId id;

    @Field(name = "model_id")
    private Integer modelId;

    @Field(name = "brand")
    private String brand;

    @Field(name = "model")
    private String model;

    @Field(name = "generation")
    private String generation;

    @Field(name = "year")
    private int year;

    @Field(name = "price")
    private int price;

    @Field(name = "engine_volume")
    private double engineVolume;

    @Field(name = "gearbox")
    private String gearbox;

    @Field(name = "body")
    private String body;

    @Field(name = "engine")
    private String engine;

    @Field(name = "drive")
    private String drive;

    @Field(name = "power")
    private int power;

    @Field(name = "dealer_ids")
    private Set<Integer> carDealerships = new HashSet<>();
}
