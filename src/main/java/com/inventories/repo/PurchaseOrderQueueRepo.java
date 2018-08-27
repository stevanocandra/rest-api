package com.inventories.repo;

import com.inventories.model.PoQueueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PurchaseOrderQueueRepo extends PagingAndSortingRepository<PoQueueEntity, Integer> {
    Page<PoQueueEntity> findAll(Pageable pageable);

    @Query(value = "SELECT nextval('po_queue_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
