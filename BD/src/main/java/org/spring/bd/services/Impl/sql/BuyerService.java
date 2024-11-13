package org.spring.bd.services.Impl.sql;

import org.spring.bd.core.dto.BuyerDTO;
import org.spring.bd.core.mappers.BuyerMapper;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.sql.BuyerRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("buyerService")
public class BuyerService implements ServiceInterface<BuyerDTO, Integer> {

    private final BuyerRepository buyerRepository;

    private final BuyerMapper buyerMapper;

    public BuyerService(BuyerRepository buyerRepository, BuyerMapper buyerMapper) {
        this.buyerRepository = buyerRepository;
        this.buyerMapper = buyerMapper;
    }

    @Override
    public List<BuyerDTO> getAllRecords() {
        return buyerRepository.findAll().stream()
                .map(buyerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BuyerDTO getRecordById(Integer id) {
        return buyerMapper.toDTO(
                buyerRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("buyer with id=%s not found", id)
                )
        );
    }

    @Override
    public BuyerDTO saveRecord(BuyerDTO record) {
        try {
            return buyerMapper.toDTO(
                    buyerRepository.save(buyerMapper.toEntity(record))
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("some buyer data is invalid");
        }
    }

    @Override
    public BuyerDTO updateRecord(BuyerDTO record) {
        if(!buyerRepository.existsById(record.getId())) {
            throw new ResourceNotFoundException("buyer with id=%s not found", record.getId());
        }

        return buyerMapper.toDTO(
                buyerRepository.save(buyerMapper.toEntity(record))
        );
    }

    @Override
    public void deleteRecordById(Integer id) {
        if(!buyerRepository.existsById(id)) {
            throw new ResourceNotFoundException("buyer with id=%s not found", id);
        }

        buyerRepository.deleteById(id);
    }

    @Override
    public void deleteAllRecords() {
        if (buyerRepository.count() == 0) {
            throw new ResourceNotFoundException("no buyers found");
        } else {
            buyerRepository.deleteAll();
        }
    }
}
