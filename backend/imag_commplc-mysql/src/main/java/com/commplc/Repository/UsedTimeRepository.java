package com.commplc.Repository;

import com.commplc.Entity.UsedTimeEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedTimeRepository extends JpaRepository<UsedTimeEntity, Long> {
    
}
