package com.commplc.Service;

import java.util.List;

import com.commplc.Entity.HistoryEntity;

public interface IHistoryService {

    List<HistoryEntity> findByDateAndShift(String date, String shift);

    void save(HistoryEntity history);
    
}
