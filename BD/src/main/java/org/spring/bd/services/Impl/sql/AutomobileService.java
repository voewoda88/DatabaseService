package org.spring.bd.services.Impl.sql;

import org.spring.bd.core.dto.AutomobileDTO;
import org.spring.bd.core.mappers.AutomobileMapper;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.sql.AutomobileRepository;
import org.spring.bd.repositories.sql.CarDealershipRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("automobileService")
public class AutomobileService implements ServiceInterface<AutomobileDTO, Integer> {
    private final AutomobileRepository automobileRepository;

    private final CarDealershipRepository carDealershipRepository;

    private final AutomobileMapper automobileMapper;

    @Autowired
    public AutomobileService(AutomobileRepository automobileRepository, CarDealershipRepository carDealershipRepository,
                             AutomobileMapper automobileMapper) {
        this.automobileRepository = automobileRepository;
        this.carDealershipRepository = carDealershipRepository;
        this.automobileMapper = automobileMapper;
    }

    @Override
    public List<AutomobileDTO> getAllRecords() {
        return automobileRepository.findAll().stream()
                .map(automobileMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AutomobileDTO getRecordById(Integer id) {
        return automobileMapper.toDTO(
                automobileRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("automobile with id=%s not found", id)
                )
        );
    }

    @Override
    public AutomobileDTO saveRecord(AutomobileDTO record) {
        try {
            return automobileMapper.toDTO(
                    automobileRepository.save(automobileMapper.toEntity(record, carDealershipRepository))
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("some car dealership not found");
        }
    }

    @Override
    public AutomobileDTO updateRecord(AutomobileDTO record) {
        if(!automobileRepository.existsById(record.getId())) {
            throw new ResourceNotFoundException("automobile with id=%s not found", record.getId());
        }

        return automobileMapper.toDTO(
                automobileRepository.save(automobileMapper.toEntity(record, carDealershipRepository))
        );
    }

    @Override
    public void deleteRecordById(Integer id) {
        if(!automobileRepository.existsById(id)) {
            throw new ResourceNotFoundException("automobile with id=%s not found", id);
        }

        automobileRepository.deleteById(id);
    }

    @Override
    public void deleteAllRecords() {
        if (automobileRepository.count() == 0) {
            throw new ResourceNotFoundException("no automobiles found");
        } else {
            automobileRepository.deleteAll();
        }
    }
}
