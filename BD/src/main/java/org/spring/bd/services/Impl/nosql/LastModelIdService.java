package org.spring.bd.services.Impl.nosql;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.spring.bd.entities.nosql.LastModelId;
import org.spring.bd.repositories.nosql.LastModelIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class LastModelIdService {

    private final LastModelIdRepository lastModelIdRepository;

    private MongoTemplate mongoTemplate;

    @Autowired
    public LastModelIdService(LastModelIdRepository lastModelIdRepository, MongoTemplate mongoTemplate) {
        this.lastModelIdRepository = lastModelIdRepository;
        this.mongoTemplate = mongoTemplate;
        initializeLastModelId();
    }

    public Integer getModelId(String fieldName) {
        LastModelId lastModelId = lastModelIdRepository.findTopByOrderByIdDesc();
        try {
            Field field = LastModelId.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            Integer currentValue = (Integer) field.get(lastModelId);

            Integer newModelId = currentValue + 1;

            field.set(lastModelId, newModelId);

            updateLastModelIdById(lastModelId.getId(), lastModelId);

            return newModelId;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateLastModelIdById(ObjectId id, LastModelId lastModelId) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();

        update.set("last_automobile_model_id", lastModelId.getAutomobileModelId());
        update.set("last_car_dealership_model_id", lastModelId.getCarDealershipModelId());
        update.set("last_employee_model_id", lastModelId.getEmployeeModelId());
        update.set("last_buyer_model_id", lastModelId.getBuyerModelId());
        update.set("last_order_model_id", lastModelId.getOrderModelId());

        mongoTemplate.updateFirst(query, update, LastModelId.class);
    }

    private void initializeLastModelId() {
        if(lastModelIdRepository.count() != 0) {
            return;
        }

        LastModelId lastModelId = LastModelId.builder()
                .automobileModelId(0)
                .buyerModelId(0)
                .carDealershipModelId(0)
                .orderModelId(0)
                .employeeModelId(0)
                .build();

        List<String> collections = List.of("automobiles", "buyer", "car_dealership", "employee", "order");

        for(String collection : collections) {
            Query query = new Query();
            query.fields().include("model_id");
            query.with(Sort.by(Sort.Direction.DESC, "model_id"));
            query.limit(1);

            Document maxModelIdDoc = mongoTemplate.findOne(query, Document.class, collection);
            Integer maxModelId = maxModelIdDoc != null ? maxModelIdDoc.getInteger("model_id") : 0;

            lastModelIdValueSetter(collection, maxModelId, lastModelId);
        }

        lastModelIdRepository.save(lastModelId);
    }

    private void lastModelIdValueSetter(String collection, Integer maxModelId, LastModelId lastModelId) {
        switch (collection) {
            case "automobiles":
                lastModelId.setAutomobileModelId(maxModelId);
                break;
            case "buyer":
                lastModelId.setBuyerModelId(maxModelId);
                break;
            case "car_dealership":
                lastModelId.setCarDealershipModelId(maxModelId);
                break;
            case "employee":
                lastModelId.setEmployeeModelId(maxModelId);
                break;
            case "order":
                lastModelId.setOrderModelId(maxModelId);
                break;
        }
    }




}
