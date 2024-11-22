package org.spring.bd.controllers.api.mongo;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractEmployeeController;
import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/employee")
@Tag(name = "MongoEmployeeController", description = "Provides CRUD-operations with records in \\\"employee\\\" table")
public class MongoEmployeeApiController extends AbstractEmployeeController {
    @Autowired
    public MongoEmployeeApiController(@Qualifier("mongoEmployeeService") ServiceInterface<EmployeeDTO, Integer> employeeService) {
        super(employeeService);
    }
}
