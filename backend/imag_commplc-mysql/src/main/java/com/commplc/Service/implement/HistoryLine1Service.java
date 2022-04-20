package com.commplc.Service.implement;

import java.util.List;

import com.commplc.Entity.HistoryLine1Entity;
import com.commplc.Repository.HistoryLine1Repository;
import com.commplc.Service.IHistoryLine1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryLine1Service implements IHistoryLine1Service {

    @Autowired
    private HistoryLine1Repository historyRepository;

    @Override
    public void save(HistoryLine1Entity history) {
        historyRepository.save(history);
    }

    @Override
    public List<HistoryLine1Entity> findByDateAndShiftOrderByIdDesc(String date, String shift) {
        historyRepository.findById(1L);
        return historyRepository.findByDateAndShiftOrderByIdDesc(date, shift);
    }
    
}
