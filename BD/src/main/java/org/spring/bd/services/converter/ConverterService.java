package org.spring.bd.services.converter;

import lombok.RequiredArgsConstructor;
import org.spring.bd.core.mappers.*;
import org.spring.bd.entities.nosql.*;
import org.spring.bd.entities.sql.*;
import org.spring.bd.repositories.nosql.*;
import org.spring.bd.repositories.sql.*;
import org.spring.bd.services.Impl.nosql.LastModelIdService;
import org.spring.bd.utils.EntityComparator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConverterService {
    private final MongoEmployeeRepository mongoEmployeeRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private final MongoAutomobileRepository mongoAutomobileRepository;
    private final AutomobileRepository automobileRepository;
    private final AutomobileMapper automobileMapper;

    private final MongoCarDealershipRepository mongoCarDealershipRepository;
    private final CarDealershipRepository carDealershipRepository;
    private final CarDealershipMapper carDealershipMapper;

    private final MongoBuyerRepository mongoBuyerRepository;
    private final BuyerRepository buyerRepository;
    private final BuyerMapper buyerMapper;

    private final MongoOrderRepository mongoOrderRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final LastModelIdService lastModelIdService;

    public void ConvertPostgresToMongo() {
        convertAutomobiles();
        convertCarDealership();
        convertBuyer();
        convertEmployee();
        convertOrder();
    }

    private void convertAutomobiles() {
        List<Automobile> automobiles = automobileRepository.findAll();
        List<MongoAutomobile> mongoAutomobiles = mongoAutomobileRepository.findAll();

        for(Automobile automobile : automobiles) {
            boolean isAutomobilePresent = false;
            for(MongoAutomobile mongoAutomobile : mongoAutomobiles) {
                if(EntityComparator.areAutomobilesEquals(automobile, mongoAutomobile)) {
                    isAutomobilePresent = true;
                    break;
                }
            }

            if(!isAutomobilePresent) {
                MongoAutomobile mongoAutomobile = automobileMapper.entityToMongo(automobile);
                mongoAutomobile.setModelId(lastModelIdService.getModelId("automobileModelId"));
                mongoAutomobileRepository.save(mongoAutomobile);
            }
        }
    }

    private void convertCarDealership() {
        List<CarDealership> carDealerships = carDealershipRepository.findAll();
        List<MongoCarDealership> mongoCarDealerships = mongoCarDealershipRepository.findAll();

        for(CarDealership carDealership : carDealerships) {
            boolean isCarDealershipPresent = false;
            for(MongoCarDealership mongoCarDealership : mongoCarDealerships) {
                if(EntityComparator.areCarDealershipsEqual(carDealership, mongoCarDealership)) {
                    isCarDealershipPresent = true;
                    break;
                }
            }

            if(!isCarDealershipPresent) {
                MongoCarDealership mongoCarDealership = carDealershipMapper.entityToMongo(carDealership);
                mongoCarDealership.setModelId(lastModelIdService.getModelId("carDealershipModelId"));
                mongoCarDealershipRepository.save(mongoCarDealership);
            }
        }
    }

    private void convertBuyer() {
        List<Buyer> buyers = buyerRepository.findAll();
        List<MongoBuyer> mongoBuyers = mongoBuyerRepository.findAll();

        for(Buyer buyer : buyers) {
            boolean isBuyerPresent = false;
            for(MongoBuyer mongoBuyer : mongoBuyers) {
                if(EntityComparator.areBuyersEqual(buyer, mongoBuyer)) {
                    isBuyerPresent = true;
                    break;
                }
            }

            if(!isBuyerPresent) {
                MongoBuyer mongoBuyer = buyerMapper.entityToMongo(buyer);
                mongoBuyer.setModelId(lastModelIdService.getModelId("buyerModelId"));
                mongoBuyerRepository.save(mongoBuyer);
            }
        }
    }

    private void convertEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<MongoEmployee> mongoEmployees = mongoEmployeeRepository.findAll();

        for(Employee employee : employees) {
            boolean isEmployeePresent = false;
            for(MongoEmployee mongoEmployee : mongoEmployees) {
                if(EntityComparator.areEmployeesEqual(employee, mongoEmployee)) {
                    isEmployeePresent = true;
                    break;
                }
            }

            if(!isEmployeePresent) {
                MongoEmployee mongoEmployee = employeeMapper.entityToMongo(employee);
                mongoEmployee.setModelId(lastModelIdService.getModelId("employeeModelId"));
                mongoEmployeeRepository.save(mongoEmployee);
            }
        }
    }

    private void convertOrder() {
        List<Order> orders = orderRepository.findAll();
        List<MongoOrder> mongoOrders = mongoOrderRepository.findAll();

        for(Order order : orders) {
            boolean isOrderPresent = false;
            for(MongoOrder mongoOrder : mongoOrders) {
                if(EntityComparator.areOrdersEqual(order, mongoOrder)) {
                    isOrderPresent = true;
                    break;
                }
            }

            if(!isOrderPresent) {
                MongoOrder mongoOrder = orderMapper.entityToMongo(order);
                mongoOrder.setModelId(lastModelIdService.getModelId("orderModelId"));
                mongoOrderRepository.save(mongoOrder);
            }
        }
    }
}
