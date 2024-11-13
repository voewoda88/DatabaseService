package org.spring.bd.core.mappers;

import org.mapstruct.Mapper;
import org.spring.bd.core.dto.BuyerDTO;
import org.spring.bd.entities.sql.Buyer;

@Mapper(componentModel = "spring")
public interface BuyerMapper {
    BuyerDTO toDTO(Buyer buyer);

    Buyer toEntity(BuyerDTO buyerDTO);
}
