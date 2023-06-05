package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Magazine;
import com.example.biblioteca.model.entity.RecordCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecordCompanyRepository extends JpaRepository<RecordCompany,Long> {
    RecordCompany findByNameEqualsIgnoreCase (String name);
}
