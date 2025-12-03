package com.jmtebar.applications.api.model;

import com.jmtebar.applications.api.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CompanyTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void testInsertCompany() {
        Company company = new Company("Test Company", 4.5, "https://example.com/test");
        Company savedCompany = companyRepository.save(company);

        assertThat(savedCompany).isNotNull();
        assertThat(savedCompany.getId()).isNotNull();
        assertThat(savedCompany.getName()).isEqualTo("Test Company");
        assertThat(savedCompany.getRating()).isEqualTo(4.5);
        assertThat(savedCompany.getGlassdoorLink()).isEqualTo("https://example.com/test");
    }

    @Test
    void testSearchCompanyById() {
        Company company = new Company("Search Test", 3.8, "https://example.com/search");
        Company savedCompany = entityManager.persistAndFlush(company);
        Integer companyId = savedCompany.getId();

        Optional<Company> foundCompany = companyRepository.findById(companyId);

        assertThat(foundCompany).isPresent();
        assertThat(foundCompany.get().getName()).isEqualTo("Search Test");
        assertThat(foundCompany.get().getRating()).isEqualTo(3.8);
        assertThat(foundCompany.get().getGlassdoorLink()).isEqualTo("https://example.com/search");
    }

    @Test
    void testSearchAllCompanies() {
        Company company1 = new Company("Company One", 4.0, "https://example.com/one");
        Company company2 = new Company("Company Two", 4.5, "https://example.com/two");
        entityManager.persist(company1);
        entityManager.persist(company2);
        entityManager.flush();

        List<Company> companies = companyRepository.findAll();

        assertThat(companies).isNotEmpty();
        assertThat(companies).hasSizeGreaterThanOrEqualTo(2);
        assertThat(companies).extracting(Company::getName)
                .contains("Company One", "Company Two");
    }

    @Test
    void testUpdateCompany() {
        Company company = new Company("Original Name", 3.0, "https://example.com/original");
        Company savedCompany = entityManager.persistAndFlush(company);
        Integer companyId = savedCompany.getId();

        savedCompany.setName("Updated Name");
        savedCompany.setRating(4.8);
        savedCompany.setGlassdoorLink("https://example.com/updated");
        Company updatedCompany = companyRepository.save(savedCompany);
        entityManager.flush();

        assertThat(updatedCompany.getId()).isEqualTo(companyId);
        assertThat(updatedCompany.getName()).isEqualTo("Updated Name");
        assertThat(updatedCompany.getRating()).isEqualTo(4.8);
        assertThat(updatedCompany.getGlassdoorLink()).isEqualTo("https://example.com/updated");

        entityManager.clear();
        Company persistedCompany = entityManager.find(Company.class, companyId);
        assertThat(persistedCompany.getName()).isEqualTo("Updated Name");
        assertThat(persistedCompany.getRating()).isEqualTo(4.8);
    }

    @Test
    void testDeleteCompany() {
        Company company = new Company("To Be Deleted", 3.5, "https://example.com/delete");
        Company savedCompany = entityManager.persistAndFlush(company);
        Integer companyId = savedCompany.getId();

        assertThat(companyRepository.findById(companyId)).isPresent();

        companyRepository.deleteById(companyId);
        entityManager.flush();

        Optional<Company> deletedCompany = companyRepository.findById(companyId);
        assertThat(deletedCompany).isNotPresent();
    }

    @Test
    void testDeleteCompanyEntity() {
        Company company = new Company("To Be Deleted Entity", 2.5, "https://example.com/delete-entity");
        Company savedCompany = entityManager.persistAndFlush(company);
        Integer companyId = savedCompany.getId();

        companyRepository.delete(savedCompany);
        entityManager.flush();

        Optional<Company> deletedCompany = companyRepository.findById(companyId);
        assertThat(deletedCompany).isNotPresent();
    }

    @Test
    void testSearchNonExistentCompany() {
        Optional<Company> company = companyRepository.findById(99999);

        assertThat(company).isNotPresent();
    }

    @Test
    void testCountCompanies() {
        Company company1 = new Company("Count Test 1", 4.0, "https://example.com/count1");
        Company company2 = new Company("Count Test 2", 4.5, "https://example.com/count2");
        entityManager.persist(company1);
        entityManager.persist(company2);
        entityManager.flush();

        long count = companyRepository.count();

        assertThat(count).isGreaterThanOrEqualTo(2);
    }
}

