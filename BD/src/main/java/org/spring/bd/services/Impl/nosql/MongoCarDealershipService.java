package org.spring.bd.services.Impl.nosql;

import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.core.mappers.CarDealershipMapper;
import org.spring.bd.entities.nosql.MongoAutomobile;
import org.spring.bd.entities.nosql.MongoCarDealership;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.nosql.MongoCarDealershipRepository;
import org.spring.bd.repositories.sql.CarDealershipRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("mongoCarDealershipService")
public class MongoCarDealershipService implements ServiceInterface<CarDealershipDTO, Integer> {

    private final MongoCarDealershipRepository mongoCarDealershipRepository;

    private final CarDealershipMapper carDealershipMapper;

    private final LastModelIdService lastModelIdService;

    @Autowired
    public MongoCarDealershipService(MongoCarDealershipRepository mongoCarDealershipRepository, CarDealershipMapper carDealershipMapper,
                                     LastModelIdService lastModelIdService) {
            this.mongoCarDealershipRepository = mongoCarDealershipRepository;
            this.carDealershipMapper = carDealershipMapper;
            this.lastModelIdService = lastModelIdService;
    }

    @Override
    public List<CarDealershipDTO> getAllRecords() {
        return mongoCarDealershipRepository.findAll().stream()
                .map(carDealershipMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDealershipDTO getRecordById(Integer id) {
        return carDealershipMapper.toDTO(
                mongoCarDealershipRepository.findByModelId(id).orElseThrow(
                        () -> new ResourceNotFoundException("car dealership with id=%s not found", id)
                )
        );
    }

    @Override
    public CarDealershipDTO saveRecord(CarDealershipDTO record) {
        try {
            MongoCarDealership carDealership = carDealershipMapper.toEntity(record);
            carDealership.setModelId(lastModelIdService.getModelId("carDealershipModelId"));

            return carDealershipMapper.toDTO(
                    mongoCarDealershipRepository.save(carDealership)
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("automobile save error");
        }
    }

    @Override
    public CarDealershipDTO updateRecord(CarDealershipDTO record) {
        if(!mongoCarDealershipRepository.existsByModelId(record.getId())) {
            throw new ResourceNotFoundException("car dealership with id=%s not found", record.getId());
        }

        try {
            return carDealershipMapper.toDTO(
                    mongoCarDealershipRepository.save(carDealershipMapper.toEntity(record))
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("car dealership save error");
        }
    }

    @Override
    public void deleteRecordById(Integer id) {
        if(!mongoCarDealershipRepository.existsByModelId(id)) {
            throw new ResourceNotFoundException("car dealership with id=%s not found", id);
        }

        mongoCarDealershipRepository.deleteByModelId(id);
    }

    @Override
    public void deleteAllRecords() {
        if(mongoCarDealershipRepository.count() == 0) {
            throw new ResourceNotFoundException("no car dealership found");
        }

        mongoCarDealershipRepository.deleteAll();
    }
}
