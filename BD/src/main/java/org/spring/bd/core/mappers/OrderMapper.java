package org.spring.bd.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.spring.bd.core.dto.OrderDTO;
import org.spring.bd.entities.nosql.MongoOrder;
import org.spring.bd.entities.sql.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "automobileId", source = "automobile.id")
    @Mapping(target = "carDealershipId", source = "carDealership.id")
    @Mapping(target = "employerId", source = "employer.id")
    @Mapping(target = "buyerId", source = "buyer.id")
    OrderDTO toDTO(Order order);

    @Mapping(target = "automobile.id", source = "automobileId")
    @Mapping(target = "carDealership.id", source = "carDealershipId")
    @Mapping(target = "employer.id", source = "employerId")
    @Mapping(target = "buyer.id", source = "buyerId")
    Order toEntity(OrderDTO orderDTO);

    @Mapping(target = "id", source = "modelId")
    OrderDTO toDTO(MongoOrder mongoOrder);

    @Mapping(target = "modelId", source = "id")
    @Mapping(target = "id", ignore = true)
    MongoOrder toMongoEntity(OrderDTO orderDTO);

    @Mapping(target = "automobileId", source = "automobile.id")
    @Mapping(target = "carDealershipId", source = "carDealership.id")
    @Mapping(target = "employerId", source = "employer.id")
    @Mapping(target = "buyerId", source = "buyer.id")
    @Mapping(target = "id", ignore = true)
    MongoOrder entityToMongo(Order order);
}
