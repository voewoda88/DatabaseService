package org.spring.bd.core.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.entities.sql.Automobile;
import org.spring.bd.entities.sql.CarDealership;
import org.spring.bd.repositories.sql.AutomobileRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {AutomobileRepository.class})
public interface CarDealershipMapper {
    CarDealershipMapper INSTANCE = Mappers.getMapper(CarDealershipMapper.class);

    @Mapping(target = "automobileIds", source = "automobiles", qualifiedByName = "mapAutomobilesToIds")
    CarDealershipDTO toDTO(CarDealership carDealership);

    @Mapping(target = "automobiles", expression = "java(mapIdsToAutomobiles(carDealershipDTO.getAutomobileIds(), automobileRepository))")
    CarDealership toEntity(CarDealershipDTO carDealershipDTO, @Context AutomobileRepository automobileRepository);

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
    default Set<Automobile> mapIdsToAutomobiles(Set<Integer> ids, AutomobileRepository automobileRepository) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(id -> {
                    Optional<Automobile> optionalAutomobile = automobileRepository.findById(id);
                    Automobile automobile = optionalAutomobile.get();
                    return automobile;
                })
                .collect(Collectors.toSet());
    }
}
