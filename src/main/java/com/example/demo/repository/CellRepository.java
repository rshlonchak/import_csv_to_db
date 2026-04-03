package com.example.demo.repository;

import com.example.demo.model.OpenCellId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends CrudRepository<OpenCellId, Long> {
}
