package com.commplc.Service.implement;

import com.commplc.Entity.Line1Entity;
import com.commplc.Repository.Line1Repository;
import com.commplc.Service.ILine1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Line1Service implements ILine1Service {

    @Autowired
    private Line1Repository line1Repository;

    @Override
    public void truncateTable() {
        line1Repository.truncateTable();
    }

    @Override
    public void save(Line1Entity line1Entity) {
        line1Repository.save(line1Entity);
    }
    
}
