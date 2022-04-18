package com.commplc.Repository;

import java.util.List;

import com.commplc.Entity.HistoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

    List<HistoryEntity> findByDateAndShift(String date, String shift);

}