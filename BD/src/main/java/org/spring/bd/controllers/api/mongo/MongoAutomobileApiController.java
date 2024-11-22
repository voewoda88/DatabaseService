package org.spring.bd.controllers.api.mongo;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractAutomobileControllers;
import org.spring.bd.core.dto.AutomobileDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/automobile")
@Tag(name = "MongoAutomobileController", description = "Provides CRUD-operations with records in \\\"automobiles\\\" table")
public class MongoAutomobileApiController extends AbstractAutomobileControllers {
    @Autowired
    public MongoAutomobileApiController(@Qualifier("mongoAutomobileService")ServiceInterface<AutomobileDTO, Integer> automobileService) {
        super(automobileService);
    }
}
