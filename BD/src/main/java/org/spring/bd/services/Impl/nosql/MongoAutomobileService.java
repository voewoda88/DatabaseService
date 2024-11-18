package org.spring.bd.services.Impl.nosql;

import org.spring.bd.core.dto.AutomobileDTO;
import org.spring.bd.core.mappers.AutomobileMapper;
import org.spring.bd.entities.nosql.MongoAutomobile;
import org.spring.bd.exceptions.InternalServerException;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.nosql.MongoAutomobileRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("mongoAutomobileService")
public class MongoAutomobileService implements ServiceInterface<AutomobileDTO, Integer> {

    private final MongoAutomobileRepository mongoAutomobileRepository;

    private final AutomobileMapper automobileMapper;

    private final LastModelIdService lastModelIdService;

    @Autowired
    public MongoAutomobileService(MongoAutomobileRepository mongoAutomobileRepository, AutomobileMapper automobileMapper,
                                  LastModelIdService lastModelIdService) {
        this.mongoAutomobileRepository = mongoAutomobileRepository;
        this.automobileMapper = automobileMapper;
        this.lastModelIdService = lastModelIdService;
    }

    @Override
    public List<AutomobileDTO> getAllRecords() {
        return mongoAutomobileRepository.findAll().stream()
                .map(automobileMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AutomobileDTO getRecordById(Integer id) {
        return automobileMapper.toDTO(
                mongoAutomobileRepository.findByModelId(id).orElseThrow(
                        () -> new ResourceNotFoundException("automobile with id=%s not found", id)
                )
        );
    }

    @Override
    public AutomobileDTO saveRecord(AutomobileDTO record) {
        try {
            MongoAutomobile automobile = automobileMapper.toEntity(record);
            automobile.setModelId(lastModelIdService.getModelId("automobileModelId"));

            return automobileMapper.toDTO(
                    mongoAutomobileRepository.save(automobile)
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("automobile save error");
        }
    }

    @Override
    public AutomobileDTO updateRecord(AutomobileDTO record) {
        if(!mongoAutomobileRepository.existsByModelId(record.getId())) {
            throw new ResourceNotFoundException("automobile with id=%s not found", record.getId());
        }

        try {
            return automobileMapper.toDTO(
                    mongoAutomobileRepository.save(automobileMapper.toEntity(record))
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("automobile save error");
        }
    }

    @Override
    public void deleteRecordById(Integer id) {
        if(!mongoAutomobileRepository.existsByModelId(id)) {
            throw new ResourceNotFoundException("automobile with id=%s not found", id);
        }

        mongoAutomobileRepository.deleteByModelId(id);
    }

    @Override
    public void deleteAllRecords() {
        if(mongoAutomobileRepository.count() == 0) {
            throw new ResourceNotFoundException("no automobiles found");
        }

        mongoAutomobileRepository.deleteAll();
    }
}
