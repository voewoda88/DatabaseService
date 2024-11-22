package org.spring.bd.repositories.nosql;


import org.bson.types.ObjectId;
import org.spring.bd.entities.nosql.MongoBuyer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoBuyerRepository extends MongoRepository<MongoBuyer, ObjectId> {
    Optional<MongoBuyer> findByModelId(Integer id);

    boolean existsByModelId(Integer id);

    void deleteByModelId(Integer id);
}
