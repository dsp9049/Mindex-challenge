package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.ReportingStructureRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private ReportingStructureRepository reportingStructureRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        //now that I know whats going on
        LOG.debug("Reading reportStructure with employee id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        LOG.debug("Read: posa " + employee.getFirstName()+ " " + employee.getLastName());

       //old

        LOG.debug("Reading reportStructure with employee id [{}]", id);

        ReportingStructure reportingStructure = reportingStructureRepository.findByEmployeeId(id);
        employee = employeeRepository.findByEmployeeId(id);

        if (reportingStructure == null && employee == null) {
            throw new RuntimeException("Invalid reportingStructure for employee: " + id);
        }

        reportingStructure = new ReportingStructure(employee);
        return reportingStructure;
    }
}
