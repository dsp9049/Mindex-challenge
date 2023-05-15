package com.mindex.challenge.dao;

import com.mindex.challenge.data.ReportingStructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportingStructureRepository extends MongoRepository<ReportingStructure, String> {
    ReportingStructure findByEmployeeId(String employeeId);
}
