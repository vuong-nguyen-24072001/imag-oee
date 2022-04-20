package com.commplc.Repository;
import com.commplc.Entity.Line1Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface Line1Repository extends JpaRepository<Line1Entity, Long> {
    
    @Modifying
    @Query(
            value = "truncate table line1",
            nativeQuery = true
    )
    void truncateTable();

}
