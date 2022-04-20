package com.commplc.Repository;

import java.util.List;

import com.commplc.Entity.HistoryLine1Entity;

import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryLine1Repository extends JpaRepository<HistoryLine1Entity, Long> {

    List<HistoryLine1Entity> findByDateAndShiftOrderByIdDesc(String date, String shift);

}