package com.jmtebar.applications.company;

import com.jmtebar.applications.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "company")
@Data
@AllArgsConstructor
public class Company extends BaseEntity {

    private String name;
    private double rating;
    private String glassdoorLink;

}
