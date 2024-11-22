package org.spring.bd.services.Impl.nosql;

import org.spring.bd.core.dto.OrderDTO;
import org.spring.bd.core.mappers.OrderMapper;
import org.spring.bd.entities.nosql.MongoOrder;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.nosql.MongoOrderRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("mongoOrderService")
public class MongoOrderService implements ServiceInterface<OrderDTO, Integer> {

    private final MongoOrderRepository mongoOrderRepository;

    private final OrderMapper orderMapper;

    private final LastModelIdService lastModelIdService;

    @Autowired
    public MongoOrderService(MongoOrderRepository mongoOrderRepository, OrderMapper orderMapper,
                                LastModelIdService lastModelIdService){
        this.mongoOrderRepository = mongoOrderRepository;
        this.orderMapper = orderMapper;
        this.lastModelIdService = lastModelIdService;
    }

    @Override
    public List<OrderDTO> getAllRecords() {
        return mongoOrderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getRecordById(Integer id) {
        return orderMapper.toDTO(
                mongoOrderRepository.findByModelId(id).orElseThrow(
                        () -> new ResourceNotFoundException("order with id=%s not found", id)
                )
        );
    }

    @Override
    public OrderDTO saveRecord(OrderDTO record) {
        try {
            MongoOrder order = orderMapper.toMongoEntity(record);
            order.setModelId(lastModelIdService.getModelId("orderModelId"));

            return orderMapper.toDTO(
                    mongoOrderRepository.save(order)
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("order save error");
        }
    }

    @Override
    public OrderDTO updateRecord(OrderDTO record) {
        if(!mongoOrderRepository.existsByModelId(record.getId())) {
            throw new ResourceNotFoundException("order with id=%s not found", record.getId());
        }

        try {
            return orderMapper.toDTO(
                    mongoOrderRepository.save(orderMapper.toMongoEntity(record))
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("order save error");
        }
    }

    @Override
    public void deleteRecordById(Integer id) {
        if(!mongoOrderRepository.existsByModelId(id)) {
            throw new ResourceNotFoundException("order with id=%s not found", id);
        }

        mongoOrderRepository.deleteByModelId(id);
    }

    @Override
    public void deleteAllRecords() {
        if(mongoOrderRepository.count() == 0) {
            throw new ResourceNotFoundException("no orders found");
        }

        mongoOrderRepository.deleteAll();
    }
}
