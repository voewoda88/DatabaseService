package org.spring.bd.services.Impl.nosql;


import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.core.mappers.EmployeeMapper;
import org.spring.bd.entities.nosql.MongoEmployee;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.nosql.MongoEmployeeRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("mongoEmployeeService")
public class MongoEmployeeService implements ServiceInterface<EmployeeDTO, Integer> {

    private final MongoEmployeeRepository mongoEmployeeRepository;

    private final EmployeeMapper employeeMapper;

    private final LastModelIdService lastModelIdService;

    @Autowired
    public MongoEmployeeService(MongoEmployeeRepository mongoEmployeeRepository, EmployeeMapper employeeMapper,
                             LastModelIdService lastModelIdService){
        this.mongoEmployeeRepository = mongoEmployeeRepository;
        this.employeeMapper = employeeMapper;
        this.lastModelIdService = lastModelIdService;
    }

    @Override
    public List<EmployeeDTO> getAllRecords() {
        return mongoEmployeeRepository.findAll().stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getRecordById(Integer id) {
        return employeeMapper.toDTO(
                mongoEmployeeRepository.findByModelId(id).orElseThrow(
                        () -> new ResourceNotFoundException("employee with id=%s not found", id)
                )
        );
    }

    @Override
    public EmployeeDTO saveRecord(EmployeeDTO record) {
        try {
            MongoEmployee employee = employeeMapper.toMongoEntity(record);
            employee.setModelId(lastModelIdService.getModelId("employeeModelId"));

            return employeeMapper.toDTO(
                    mongoEmployeeRepository.save(employee)
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("employee save error");
        }
    }

    @Override
    public EmployeeDTO updateRecord(EmployeeDTO record) {
        if(!mongoEmployeeRepository.existsByModelId(record.getId())) {
            throw new ResourceNotFoundException("employee with id=%s not found", record.getId());
        }

        try {
            return employeeMapper.toDTO(
                    mongoEmployeeRepository.save(employeeMapper.toMongoEntity(record))
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("employee save error");
        }
    }

    @Override
    public void deleteRecordById(Integer id) {
        if(!mongoEmployeeRepository.existsByModelId(id)) {
            throw new ResourceNotFoundException("employee with id=%s not found", id);
        }

        mongoEmployeeRepository.deleteByModelId(id);
    }

    @Override
    public void deleteAllRecords() {
        if(mongoEmployeeRepository.count() == 0) {
            throw new ResourceNotFoundException("no employers found");
        }

        mongoEmployeeRepository.deleteAll();
    }
}
