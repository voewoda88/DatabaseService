package org.spring.bd.controllers.api.mongo;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractBuyerController;
import org.spring.bd.core.dto.BuyerDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/buyer")
@Tag(name = "MongoBuyerController", description = "Provides CRUD-operations with records in \\\"buyer\\\" table")
public class MongoBuyerApiController extends AbstractBuyerController {
    @Autowired
    public MongoBuyerApiController(@Qualifier("mongoBuyerService") ServiceInterface<BuyerDTO, Integer> buyerService) {
        super(buyerService);
    }
}
