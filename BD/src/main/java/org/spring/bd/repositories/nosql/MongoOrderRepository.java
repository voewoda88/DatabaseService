package org.spring.bd.repositories.nosql;

import org.bson.types.ObjectId;
import org.spring.bd.entities.nosql.MongoOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoOrderRepository extends MongoRepository<MongoOrder, ObjectId> {
    Optional<MongoOrder> findByModelId(Integer id);

    boolean existsByModelId(Integer id);

    void deleteByModelId(Integer id);
}
