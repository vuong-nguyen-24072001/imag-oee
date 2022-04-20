package com.commplc.Service;

import java.util.List;

import com.commplc.Entity.HistoryLine1Entity;

public interface IHistoryLine1Service {

    void save(HistoryLine1Entity history);

    List<HistoryLine1Entity> findByDateAndShiftOrderByIdDesc(String date, String shift);
    
}
