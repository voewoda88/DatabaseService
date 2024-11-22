package org.spring.bd.repositories.nosql;

import org.bson.types.ObjectId;
import org.spring.bd.entities.nosql.MongoCarDealership;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoCarDealershipRepository extends MongoRepository<MongoCarDealership, ObjectId> {
    Optional<MongoCarDealership> findByModelId(Integer id);

    boolean existsByModelId(Integer id);

    void deleteByModelId(Integer id);
}
