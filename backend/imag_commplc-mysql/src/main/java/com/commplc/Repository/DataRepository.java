package com.commplc.Repository;

import com.commplc.Entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DataRepository extends JpaRepository<DataEntity, Long> {

    @Modifying
    @Query(
            value = "truncate table data",
            nativeQuery = true
    )
    void truncateTable();

}
