package com.bta;

import com.bta.domain.Country;
import com.bta.repository.CountryRepository;
import com.bta.repository.CountryRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CountryRepository countryRepository = new CountryRepositoryImpl();
        Country countryToSave = new Country("Spain", 70_000_000);
        boolean isSaved = countryRepository.save(countryToSave);
        List<Country> countries = countryRepository.findAll();
        final Country russia = countries.stream()
                .filter(c -> c.getName().equals("Russia"))
                .findFirst()
                .get();
        russia.setPopulation(1_000_000_000);
        countryRepository.update(russia);
        System.out.println(countryRepository.findAll());

        //countryRepository.deleteByName("Norway");
        countryRepository.deleteByName("Italy OR 1==1");
    }
}
