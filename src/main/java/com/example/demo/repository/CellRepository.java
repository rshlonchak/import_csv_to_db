package com.example.demo.repository;

import com.example.demo.model.OpenCellId;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CellRepository extends CrudRepository<OpenCellId, Long> {

    @Query("SELECT l FROM LbsCell l WHERE l.mcc = :mcc AND l.mnc = :mnc AND l.lac = :lac AND l.cellId = :cellId")
    Optional<OpenCellId> findByMccAndMncAndLacAndCid(Integer mcc, Integer mnc, Integer lac, Long cellId);
}
