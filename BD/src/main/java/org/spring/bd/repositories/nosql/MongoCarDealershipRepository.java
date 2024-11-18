package org.spring.bd.repositories.nosql;

import org.bson.types.ObjectId;
import org.spring.bd.entities.nosql.MongoCarDealership;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCarDealershipRepository extends MongoRepository<MongoCarDealership, ObjectId> {
}
