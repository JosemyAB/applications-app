package com.jmtebar.applications.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Company")
public class Company extends BaseEntity {

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "glassdoor_link")
    private String glassdoorLink;

    public Company() {
        // Default constructor required by JPA
    }

    public Company(String name, Double rating, String glassdoorLink) {
        this.name = name;
        this.rating = rating;
        this.glassdoorLink = glassdoorLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getGlassdoorLink() {
        return glassdoorLink;
    }

    public void setGlassdoorLink(String glassdoorLink) {
        this.glassdoorLink = glassdoorLink;
    }







}
