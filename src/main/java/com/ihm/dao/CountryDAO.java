package com.ihm.dao;
import com.ihm.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDAO extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {
}
