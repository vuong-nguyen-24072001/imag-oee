package com.commplc.Service.implement;

import com.commplc.Entity.DataEntity;
import com.commplc.Repository.DataRepository;
import com.commplc.Service.IDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService implements IDataService{

    @Autowired
    private DataRepository dataRepository;
    
    @Override
    public void truncateTable() {
        dataRepository.truncateTable();
    }

    @Override
    public void save(DataEntity data) {
        dataRepository.save(data);
    }
    
}
