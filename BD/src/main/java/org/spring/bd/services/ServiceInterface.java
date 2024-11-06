package org.spring.bd.services;

import java.util.List;

public interface ServiceInterface<T, ID> {
    List<T> getAllRecords();

    T getRecordById(ID id);

    T saveRecord(T record);

    T updateRecord(T record);

    void deleteRecordById(ID id);

    void deleteAllRecords();
}
