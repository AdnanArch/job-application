package org.adnanarch.jobapplication.company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company searchCompanyById(Long id);
    boolean updateCompany(Company updatedCompany, Long id);
    boolean deleteCompanyById(Long id);
    void createCompany(Company company);
}
