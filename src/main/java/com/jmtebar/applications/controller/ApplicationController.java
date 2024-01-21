package com.jmtebar.applications.controller;

import com.jmtebar.applications.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    private CompanyRepository companyRepository;

    // Return all the applications from the DB
    @GetMapping("/")
    public String getApplications() {


        return "All the applications";
    }

    // Return the application with the given ID from the DB
    public String getApplication(String applicationId) {
        return "The application with ID " + applicationId;
    }

    // Create an application with the given data
    public String createApplication(String applicationData) {
        return "Application created";
    }

    // Update the application with the given ID with the given data
    public String updateApplication(String applicationId, String applicationData) {
        return "Application updated";
    }

    // Delete the application with the given ID
    public String deleteApplication(String applicationId) {
        return "Application deleted";
    }
}
