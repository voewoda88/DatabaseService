package org.spring.bd.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.entities.sql.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "carDealershipId", source = "carDealership.id")
    EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "carDealership.id", source = "carDealershipId")
    Employee toEntity(EmployeeDTO employeeDTO);
}
