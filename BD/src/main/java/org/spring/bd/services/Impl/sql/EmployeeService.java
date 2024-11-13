package org.spring.bd.services.Impl.sql;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.spring.bd.core.dto.EmployeeDTO;
import org.spring.bd.core.mappers.CarDealershipMapper;
import org.spring.bd.core.mappers.EmployeeMapper;
import org.spring.bd.exceptions.ResourceNotFoundException;
import org.spring.bd.repositories.sql.CarDealershipRepository;
import org.spring.bd.repositories.sql.EmployeeRepository;
import org.spring.bd.services.ServiceInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("employeeService")
public class EmployeeService implements ServiceInterface<EmployeeDTO, Integer> {

    private final CarDealershipRepository carDealershipRepository;

    private final EmployeeMapper employeeMapper;

    private final EmployeeRepository employeeRepository;

    public EmployeeService(CarDealershipRepository carDealershipRepository, EmployeeMapper employeeMapper,
                           EmployeeRepository employeeRepository) {
        this.carDealershipRepository = carDealershipRepository;
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }


    @Override
    public List<EmployeeDTO> getAllRecords() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getRecordById(Integer id) {
        return employeeMapper.toDTO(
                employeeRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Employee with id=%s not found", id)
                )
        );
    }

    @Override
    public EmployeeDTO saveRecord(EmployeeDTO record) {
        try {
            return employeeMapper.toDTO(
                    employeeRepository.save(employeeMapper.toEntity(record))
            );
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceNotFoundException("Car dealership associated with employee not found");
        }
    }

    @Override
    public EmployeeDTO updateRecord(EmployeeDTO record) {
        if (!employeeRepository.existsById(record.getId())) {
            throw new ResourceNotFoundException("Employee with id=%s not found", record.getId());
        }

        return employeeMapper.toDTO(
                employeeRepository.save(employeeMapper.toEntity(record))
        );
    }

    @Override
    public void deleteRecordById(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee with id=%s not found", id);
        }

        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAllRecords() {
        if (employeeRepository.count() == 0) {
            throw new ResourceNotFoundException("No employees found");
        } else {
            employeeRepository.deleteAll();
        }
    }
}
