package com.commplc.Service.implement;

import java.util.List;

import com.commplc.Entity.HistoryEntity;
import com.commplc.Repository.HistoryRepository;
import com.commplc.Service.IHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService implements IHistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void save(HistoryEntity history) {
        historyRepository.save(history);
    }

    @Override
    public List<HistoryEntity> findByDateAndShift(String date, String shift) {
        return historyRepository.findByDateAndShift(date, shift);
    }
    
}
