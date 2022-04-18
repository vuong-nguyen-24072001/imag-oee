package com.commplc.Service;

import com.commplc.Entity.DataEntity;

public interface IDataService {

    void truncateTable();

    void save(DataEntity data);
    
}
