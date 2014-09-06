package com.ihm.service;

import com.ihm.dao.CountryDAO;
import com.ihm.model.Country;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	@Autowired
    CountryDAO countryDAO;

	public long countAllCountrys() {
        return countryDAO.count();
    }

	public void deleteCountry(Country country) {
        countryDAO.delete(country);
    }

	public Country findCountry(Long id) {
        return countryDAO.findOne(id);
    }

	public List<Country> findAllCountrys() {
        return countryDAO.findAll();
    }

	public List<Country> findCountryEntries(int firstResult, int maxResults) {
        return countryDAO.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveCountry(Country country) {
        countryDAO.save(country);
    }

	public Country updateCountry(Country country) {
        return countryDAO.save(country);
    }
}
