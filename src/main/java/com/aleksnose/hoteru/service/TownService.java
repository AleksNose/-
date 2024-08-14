package com.aleksnose.hoteru.service;

import com.aleksnose.hoteru.DTO.TownDTO;
import com.aleksnose.hoteru.Valid.ValidationString;
import com.aleksnose.hoteru.exception.NotFoundException;
import com.aleksnose.hoteru.models.Country;
import com.aleksnose.hoteru.models.Hotel;
import com.aleksnose.hoteru.models.Town;
import com.aleksnose.hoteru.repository.CountryRepository;
import com.aleksnose.hoteru.repository.TownRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TownService {
    private TownRepository repository;
    private CountryRepository countryRepository;

    public TownService(TownRepository repository, CountryRepository countryRepository) {
        this.repository = repository;
        this.countryRepository = countryRepository;
    }

    public Town getTownById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Town not found with id: " + id));
    }

    public Country getCountryById(Integer id) {
        return countryRepository.findById(id).orElseThrow(() -> new NotFoundException("Country not found with id: " + id));
    }

    public Set<Hotel> getHotelsByIdTown(Integer id) {
        var town = getTownById(id);
        return town.getHotels();
    }

    public Town modifiedTownById(TownDTO townDTO) {
        var town = getTownById(townDTO.getId());
        saveTown(town, townDTO);
        return town;
    }

    public Town saveTown(TownDTO townDTO) {
        var town = new Town();
        saveTown(town, townDTO);
        return town;
    }

    public List<Town> getAllTowns() {
        return repository.findAll();
    }

//    public Set<Hotel> getFreeHotels(Integer idTown, LocalDate dateFrom, LocalDate dateTo, int countPeople) {
//        var town = getTownById(idTown);
//        return town.getHotels().stream().filter(hotel -> hotel.haveFreeRooms(dateFrom, dateTo, countPeople)).collect(Collectors.toSet());
//  }

    public List<Town> getAllTownsByIdCountry(Integer idCountry) {
        return repository.findByCountryId(idCountry);
    }

    private void saveTown(Town town, TownDTO townDTO)
    {
        var country = getCountryById(townDTO.getIdCountry());
        ValidationString.isValid("name", townDTO.getName());

        town.setName(townDTO.getName());
        town.setCountry(country);
        repository.save(town);
    }
}
