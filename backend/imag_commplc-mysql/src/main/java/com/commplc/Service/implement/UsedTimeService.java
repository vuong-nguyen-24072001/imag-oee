package com.commplc.Service.implement;

import com.commplc.Entity.UsedTimeEntity;
import com.commplc.Repository.UsedTimeRepository;
import com.commplc.Service.IUsedTimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsedTimeService implements IUsedTimeService {

    @Autowired
    private UsedTimeRepository usedTimeRepository;

    @Override
    public void save(UsedTimeEntity entity) {
        usedTimeRepository.save(entity);
    }
    
}
