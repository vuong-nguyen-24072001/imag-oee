package com.commplc.Service;

import com.commplc.Entity.Line1Entity;

public interface ILine1Service {

    void truncateTable();

    void save(Line1Entity line1Entity);
    
}
