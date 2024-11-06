package org.spring.bd.services.Impl.sql;

import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.core.mappers.CarDealershipMapper;
import org.spring.bd.entities.sql.CarDealership;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.sql.CarDealershipRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("carDealershipService")
public class CarDealershipService implements ServiceInterface<CarDealershipDTO, Integer> {

    private final CarDealershipRepository carDealershipRepository;

    private final CarDealershipMapper carDealershipMapper;

    @Autowired
    public CarDealershipService(CarDealershipRepository carDealershipRepository, CarDealershipMapper carDealershipMapper) {
        this.carDealershipRepository = carDealershipRepository;
        this.carDealershipMapper = carDealershipMapper;
    }

    @Override
    public List<CarDealershipDTO> getAllRecords() {
        return carDealershipRepository.findAll().stream()
                .map(carDealershipMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDealershipDTO getRecordById(Integer id) {
        return carDealershipMapper.toDTO(
                carDealershipRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("car dealership with id=%s not found", id)
                )
        );
    }

    @Override
    public CarDealershipDTO saveRecord(CarDealershipDTO record) {
        return carDealershipMapper.toDTO(
                carDealershipRepository.save(carDealershipMapper.toEntity(record))
        );
    }

    @Override
    public CarDealershipDTO updateRecord(CarDealershipDTO record) {
        if(!carDealershipRepository.existsById(record.getId())) {
            throw new ResourceNotFoundException("car dealership with id=%s not found", record.getId());
        }

        return carDealershipMapper.toDTO(
                carDealershipRepository.save(carDealershipMapper.toEntity(record))
        );
    }

    @Override
    public void deleteRecordById(Integer id) {
        if(!carDealershipRepository.existsById(id)) {
            throw new ResourceNotFoundException("car dealership with id=%s not found", id);
        }

        carDealershipRepository.deleteById(id);
    }

    @Override
    public void deleteAllRecords() {
        if (carDealershipRepository.count() == 0) {
            throw new ResourceNotFoundException("no car dealerships found");
        } else {
            carDealershipRepository.deleteAll();
        }
    }
}
