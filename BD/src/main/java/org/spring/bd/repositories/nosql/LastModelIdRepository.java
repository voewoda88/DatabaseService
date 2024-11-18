package org.spring.bd.repositories.nosql;

import org.bson.types.ObjectId;
import org.spring.bd.entities.nosql.LastModelId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastModelIdRepository extends MongoRepository<LastModelId, ObjectId> {
    LastModelId findTopByOrderByIdDesc();
}
