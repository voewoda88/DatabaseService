package org.spring.bd.services.Impl.nosql;

import org.spring.bd.core.dto.BuyerDTO;
import org.spring.bd.core.mappers.AutomobileMapper;
import org.spring.bd.core.mappers.BuyerMapper;
import org.spring.bd.entities.nosql.MongoAutomobile;
import org.spring.bd.entities.nosql.MongoBuyer;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.nosql.MongoAutomobileRepository;
import org.spring.bd.repositories.nosql.MongoBuyerRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("mongoBuyerService")
public class MongoBuyerService implements ServiceInterface<BuyerDTO, Integer> {

    private final MongoBuyerRepository mongoBuyerRepository;

    private final BuyerMapper buyerMapper;

    private final LastModelIdService lastModelIdService;

    @Autowired
    public MongoBuyerService(MongoBuyerRepository mongoBuyerRepository, BuyerMapper buyerMapper,
                             LastModelIdService lastModelIdService){
        this.mongoBuyerRepository = mongoBuyerRepository;
        this.buyerMapper = buyerMapper;
        this.lastModelIdService = lastModelIdService;
    }

    @Override
    public List<BuyerDTO> getAllRecords() {
        return mongoBuyerRepository.findAll().stream()
                .map(buyerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BuyerDTO getRecordById(Integer id) {
        return buyerMapper.toDTO(
                mongoBuyerRepository.findByModelId(id).orElseThrow(
                        () -> new ResourceNotFoundException("buyer with id=%s not found", id)
                )
        );
    }

    @Override
    public BuyerDTO saveRecord(BuyerDTO record) {
        try {
            MongoBuyer buyer = buyerMapper.toMongoEntity(record);
            buyer.setModelId(lastModelIdService.getModelId("buyerModelId"));

            return buyerMapper.toDTO(
                    mongoBuyerRepository.save(buyer)
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("buyer save error");
        }
    }

    @Override
    public BuyerDTO updateRecord(BuyerDTO record) {
        if(!mongoBuyerRepository.existsByModelId(record.getId())) {
            throw new ResourceNotFoundException("buyer with id=%s not found", record.getId());
        }

        try {
            return buyerMapper.toDTO(
                    mongoBuyerRepository.save(buyerMapper.toMongoEntity(record))
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("buyer save error");
        }
    }

    @Override
    public void deleteRecordById(Integer id) {
        if(!mongoBuyerRepository.existsByModelId(id)) {
            throw new ResourceNotFoundException("buyer with id=%s not found", id);
        }

        mongoBuyerRepository.deleteByModelId(id);
    }

    @Override
    public void deleteAllRecords() {
        if(mongoBuyerRepository.count() == 0) {
            throw new ResourceNotFoundException("no buyers found");
        }

        mongoBuyerRepository.deleteAll();
    }
}
