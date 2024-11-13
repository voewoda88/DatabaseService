package org.spring.bd.services.Impl.sql;

import org.spring.bd.core.dto.OrderDTO;
import org.spring.bd.core.mappers.OrderMapper;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.sql.OrderRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("orderService")
public class OrderService implements ServiceInterface<OrderDTO, Integer> {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getAllRecords() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getRecordById(Integer id) {
        return orderMapper.toDTO(
                orderRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Order with id=%s not found", id)
                )
        );
    }

    @Override
    public OrderDTO saveRecord(OrderDTO record) {
        try {
            return orderMapper.toDTO(
                    orderRepository.save(orderMapper.toEntity(record)));
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("Order creation failed due to missing associated entities.");
        }
    }

    @Override
    public OrderDTO updateRecord(OrderDTO record) {
        if (!orderRepository.existsById(record.getId())) {
            throw new ResourceNotFoundException("Order with id=%s not found", record.getId());
        }

        return orderMapper.toDTO(
                orderRepository.save(orderMapper.toEntity(record))
        );
    }

    @Override
    public void deleteRecordById(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order with id=%s not found", id);
        }

        orderRepository.deleteById(id);
    }

    @Override
    public void deleteAllRecords() {
        if (orderRepository.count() == 0) {
            throw new ResourceNotFoundException("No orders found");
        } else {
            orderRepository.deleteAll();
        }
    }
}
