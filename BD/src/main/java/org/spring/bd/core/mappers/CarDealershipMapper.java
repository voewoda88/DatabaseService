package org.spring.bd.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.entities.sql.Automobile;
import org.spring.bd.entities.sql.CarDealership;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CarDealershipMapper {
    CarDealershipMapper INSTANCE = Mappers.getMapper(CarDealershipMapper.class);

    @Mapping(target = "automobileIds", source = "automobiles", qualifiedByName = "mapAutomobilesToIds")
    CarDealershipDTO toDTO(CarDealership carDealership);

    @Mapping(target = "automobiles", source = "automobileIds", qualifiedByName = "mapIdsToAutomobiles")
    CarDealership toEntity(CarDealershipDTO carDealershipDTO);

    @Named("mapAutomobilesToIds")
    default Set<Integer> mapAutomobilesToIds(Set<Automobile> automobiles) {
        if (automobiles == null) {
            return null;
        }
        return automobiles.stream()
                .map(Automobile::getId)
                .collect(Collectors.toSet());
    }

    @Named("mapIdsToAutomobiles")
    default Set<Automobile> mapIdsToAutomobiles(Set<Integer> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(id -> {
                    Automobile automobile = new Automobile();
                    automobile.setId(id);
                    return automobile;
                })
                .collect(Collectors.toSet());
    }
}
