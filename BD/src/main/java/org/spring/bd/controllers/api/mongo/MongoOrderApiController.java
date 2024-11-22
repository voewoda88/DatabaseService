package org.spring.bd.controllers.api.mongo;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractOrderController;
import org.spring.bd.core.dto.OrderDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/api/v1/order")
@Tag(name = "MongoOrderController", description = "Provides CRUD-operations with records in \\\"order\\\" table")
public class MongoOrderApiController extends AbstractOrderController {
    @Autowired
    public MongoOrderApiController(@Qualifier("mongoOrderService") ServiceInterface<OrderDTO, Integer> orderService) {
        super(orderService);
    }
}
