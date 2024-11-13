package org.spring.bd.controllers.api.postgres;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractBuyerController;
import org.spring.bd.core.dto.BuyerDTO;
import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgres/api/v1/buyer")
@Tag(name = "BuyerController", description = "Provides CRUD-operations with records in \"buyer\" table")
public class BuyerApiController extends AbstractBuyerController {
    @Autowired
    public BuyerApiController(@Qualifier("buyerService") ServiceInterface<BuyerDTO, Integer> buyerService) {
        super(buyerService);
    }
}
