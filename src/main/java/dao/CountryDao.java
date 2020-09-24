package dao;

import entity.Country;

public interface CountryDao {
    Country findCountryByName(String countryName);

    void save(Country country);
}
