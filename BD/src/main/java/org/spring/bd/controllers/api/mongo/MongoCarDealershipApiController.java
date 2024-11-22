package org.spring.bd.controllers.api.mongo;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractCarDealershipControllers;
import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/carDealership")
@Tag(name = "MongoCarDealershipController", description = "Provides CRUD-operations with records in \\\"car dealership\\\" table")
public class MongoCarDealershipApiController extends AbstractCarDealershipControllers {
    @Autowired
    public MongoCarDealershipApiController(@Qualifier("mongoCarDealershipService")ServiceInterface<CarDealershipDTO, Integer> carDealershipService) {
        super(carDealershipService);
    }
}
