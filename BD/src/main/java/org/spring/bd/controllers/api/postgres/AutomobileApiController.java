package org.spring.bd.controllers.api.postgres;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractAutomobileControllers;
import org.spring.bd.core.dto.AutomobileDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgres/api/v1/automobile")
@Tag(name = "AutomobileController", description = "Provides CRUD-operations with records in \"automobile\" table")
public class AutomobileApiController extends AbstractAutomobileControllers {
    @Autowired
    public AutomobileApiController(@Qualifier("automobileService") ServiceInterface<AutomobileDTO, Integer> automobileService) {
        super(automobileService);
    }
}
