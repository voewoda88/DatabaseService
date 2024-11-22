package org.spring.bd.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.entities.nosql.MongoEmployee;
import org.spring.bd.entities.sql.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "carDealershipId", source = "carDealership.id")
    EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "carDealership.id", source = "carDealershipId")
    Employee toEntity(EmployeeDTO employeeDTO);

    @Mapping(target = "id", source = "modelId")
    EmployeeDTO toDTO(MongoEmployee mongoEmployee);

    @Mapping(target = "modelId", source = "id")
    @Mapping(target = "id", ignore = true)
    MongoEmployee toMongoEntity(EmployeeDTO employeeDTO);

    @Mapping(target = "carDealershipId", source = "carDealership.id")
    @Mapping(target = "id", ignore = true)
    MongoEmployee entityToMongo(Employee employee);
}
