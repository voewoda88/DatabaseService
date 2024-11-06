package org.spring.bd.controllers.api.postgres;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractCarDealershipControllers;
import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.services.Impl.sql.CarDealershipService;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgres/api/v1/carDealership")
@Tag(name = "CarDealershipController", description = "Provides CRUD-operations with records in \"car dealership\" table")
public class CarDealershipApiController extends AbstractCarDealershipControllers {
    @Autowired
    public CarDealershipApiController(@Qualifier("carDealershipService")ServiceInterface<CarDealershipDTO, Integer> carDealershipService) {
        super(carDealershipService);
    }
}
