package org.spring.bd.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.spring.bd.core.dto.BuyerDTO;
import org.spring.bd.entities.nosql.MongoBuyer;
import org.spring.bd.entities.sql.Buyer;

@Mapper(componentModel = "spring")
public interface BuyerMapper {

    BuyerMapper INSTANCE = Mappers.getMapper(BuyerMapper.class);

    BuyerDTO toDTO(Buyer buyer);

    @Mapping(target = "id", source = "id")
    Buyer toEntity(BuyerDTO buyerDTO);
    @Mapping(target = "id", source = "modelId")
    BuyerDTO toDTO(MongoBuyer buyerDTO);

    @Mapping(target = "modelId", source = "id")
    @Mapping(target = "id", ignore = true)
    MongoBuyer toMongoEntity(BuyerDTO buyerDTO);

    @Mapping(target = "id", ignore = true)
    MongoBuyer entityToMongo(Buyer buyer);
}
