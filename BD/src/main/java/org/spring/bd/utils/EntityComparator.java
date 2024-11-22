package org.spring.bd.utils;

import org.spring.bd.entities.nosql.*;
import org.spring.bd.entities.sql.*;

import java.math.BigDecimal;

public class EntityComparator {
    public static boolean areAutomobilesEquals(Automobile a1, MongoAutomobile a2) {
        if (a1 == null || a2 == null) {
            return false;
        }

        return a1.getBrand().equals(a2.getBrand()) &&
                a1.getModel().equals(a2.getModel()) &&
                a1.getGeneration().equals(a2.getGeneration()) &&
                a1.getYear() == a2.getYear() &&
                a1.getPrice() == a2.getPrice() &&
                a1.getEngineVolume().doubleValue() == a2.getEngineVolume() &&
                a1.getGearbox().equals(a2.getGearbox()) &&
                a1.getBody().equals(a2.getBody()) &&
                a1.getEngine().equals(a2.getEngine()) &&
                a1.getDrive().equals(a2.getDrive()) &&
                a1.getPower() == a2.getPower();
    }

    public static boolean areBuyersEqual(Buyer b1, MongoBuyer b2) {
        if (b1 == null || b2 == null) {
            return false;
        }

        return b1.getName().equals(b2.getName()) &&
                b1.getEmail().equals(b2.getEmail()) &&
                b1.getPhoneNumber().equals(b2.getPhoneNumber());
    }

    public static boolean areCarDealershipsEqual(CarDealership c1, MongoCarDealership c2) {
        if (c1 == null || c2 == null) {
            return false;
        }

        return c1.getName().equals(c2.getName()) &&
                c1.getAddress().equals(c2.getAddress()) &&
                c1.getPhoneNumber().equals(c2.getPhoneNumber()) &&
                c1.getEmail().equals(c2.getEmail()) &&
                c1.getWorkingTime().equals(c2.getWorkingTime());
    }

    public static boolean areEmployeesEqual(Employee e1, MongoEmployee e2) {
        if (e1 == null || e2 == null) {
            return false;
        }

        return e1.getName().equals(e2.getName()) &&
                e1.getPosition().equals(e2.getPosition()) &&
                e1.getSalary().compareTo(new BigDecimal(e2.getSalary())) == 0 &&
                (e1.getCarDealership() == null ||
                        e1.getCarDealership().getId().equals(e2.getCarDealershipId()));
    }

    public static boolean areOrdersEqual(Order o1, MongoOrder o2) {
        if (o1 == null || o2 == null) {
            return false;
        }

        return o1.getStatus().equals(o2.getStatus()) &&
                o1.getCreatedDate().toString().equals(o2.getCreatedDate()) &&
                o1.getFinalValue().compareTo(new BigDecimal(o2.getFinalValue())) == 0 &&
                (o1.getAutomobile() == null ||
                        o1.getAutomobile().getId().equals(o2.getAutomobileId())) &&
                (o1.getBuyer() == null ||
                        o1.getBuyer().getId().equals(o2.getBuyerId())) &&
                (o1.getEmployer() == null ||
                        o1.getEmployer().getId().equals(o2.getEmployerId())) &&
                (o1.getCarDealership() == null ||
                        o1.getCarDealership().getId().equals(o2.getCarDealershipId()));
    }

}
