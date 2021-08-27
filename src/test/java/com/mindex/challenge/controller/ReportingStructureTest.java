package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureTest {

    private String reportingStructureIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureIdUrl = "http://localhost:" + port + "/reporting-structure/{id}";
    }

    @Test
    public void testRead() {
        // Read checks
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, employeeId).getBody();
        assertEquals(readReportingStructure.getEmployee().getEmployeeId(), employeeId);
        assertEquals(readReportingStructure.getNumberOfReports(), 4);
    }
}
