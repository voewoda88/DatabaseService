package org.spring.bd.services.Impl.nosql;

import org.spring.bd.core.dto.CarDealershipDTO;
import org.spring.bd.services.ServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mongoCarDealershipService")
public class MongoCarDealershipService implements ServiceInterface<CarDealershipDTO, Integer> {
    @Override
    public List<CarDealershipDTO> getAllRecords() {
        return null;
    }

    @Override
    public CarDealershipDTO getRecordById(Integer integer) {
        return null;
    }

    @Override
    public CarDealershipDTO saveRecord(CarDealershipDTO record) {
        return null;
    }

    @Override
    public CarDealershipDTO updateRecord(CarDealershipDTO record) {
        return null;
    }

    @Override
    public void deleteRecordById(Integer integer) {

    }

    @Override
    public void deleteAllRecords() {

    }
}
