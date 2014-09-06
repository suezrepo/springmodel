package com.ihm.service;
import com.ihm.model.Country;
import java.util.List;

public interface CountryService {

	public abstract long countAllCountrys();


	public abstract void deleteCountry(Country country);


	public abstract Country findCountry(Long id);


	public abstract List<Country> findAllCountrys();


	public abstract List<Country> findCountryEntries(int firstResult, int maxResults);


	public abstract void saveCountry(Country country);


	public abstract Country updateCountry(Country country);

}
