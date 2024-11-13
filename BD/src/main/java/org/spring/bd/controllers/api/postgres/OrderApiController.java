package org.spring.bd.controllers.api.postgres;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.controllers.abstracts.AbstractOrderController;
import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.core.dto.OrderDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postgres/api/v1/order")
@Tag(name = "OrderController", description = "Provides CRUD-operations with records in \"order\" table")
public class OrderApiController extends AbstractOrderController {
    @Autowired
    public OrderApiController(@Qualifier("orderService") ServiceInterface<OrderDTO, Integer> orderService) {
        super(orderService);
    }
}
