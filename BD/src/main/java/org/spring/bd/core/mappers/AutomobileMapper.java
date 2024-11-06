package org.spring.bd.core.mappers;

import io.swagger.v3.oas.annotations.media.Content;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.spring.bd.core.dto.AutomobileDTO;
import org.spring.bd.entities.sql.Automobile;
import org.spring.bd.entities.sql.CarDealership;
import org.spring.bd.repositories.sql.CarDealershipRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {CarDealershipRepository.class})
public interface AutomobileMapper {
    AutomobileMapper INSTANCE = Mappers.getMapper(AutomobileMapper.class);

    @Mapping(target = "carDealershipIds", source = "carDealerships", qualifiedByName = "mapCarDealershipsToIds")
    AutomobileDTO toDTO(Automobile automobile);

    @Mapping(target = "carDealerships", expression = "java(addCarDealerships(automobileDTO.getCarDealershipIds(), carDealershipRepository))")
    Automobile toEntity(AutomobileDTO automobileDTO, @Context CarDealershipRepository carDealershipRepository);

    @Named("mapCarDealershipsToIds")
    default Set<Integer> mapCarDealershipsToIds(Set<CarDealership> carDealerships) {
        return carDealerships.stream()
                .map(CarDealership::getId)
                .collect(Collectors.toSet());
    }

    default Set<CarDealership> addCarDealerships(Set<Integer> ids, CarDealershipRepository carDealershipRepository) {
        Set<CarDealership> carDealerships = new HashSet<>();
        ids.stream()
                .map(carDealershipRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(carDealerships::add);
        return carDealerships;
    }
}
