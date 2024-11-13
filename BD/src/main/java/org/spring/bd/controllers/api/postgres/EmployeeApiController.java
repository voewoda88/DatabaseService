package org.spring.bd.controllers.api.postgres;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractEmployeeController;
import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgres/api/v1/employee")
@Tag(name = "EmployeeController", description = "Provides CRUD-operations with records in \"employee\" table")
public class EmployeeApiController extends AbstractEmployeeController {
    @Autowired
    public EmployeeApiController(@Qualifier("employeeService") ServiceInterface<EmployeeDTO, Integer> employeeService) {
        super(employeeService);
    }
}
