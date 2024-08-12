package com.aleksnose.hoteru.service;

import com.aleksnose.hoteru.exception.NotFoundException;
import com.aleksnose.hoteru.models.Country;
import com.aleksnose.hoteru.models.Hotel;
import com.aleksnose.hoteru.models.Town;
import com.aleksnose.hoteru.repository.TownRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TownService {
    private TownRepository townRepository;

    public TownService(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    public Town getTownById(Integer id) {
        return townRepository.findById(id).orElseThrow(() -> new NotFoundException("Town not found with id: " + id));
    }

    public Set<Hotel> getHotelsByIdTown(Integer id) {
        var town = getTownById(id);
        return town.getHotels();
    }

//    public Set<Hotel> getFreeHotels(Integer idTown, LocalDate dateFrom, LocalDate dateTo, int countPeople) {
//        var town = getTownById(idTown);
//        return town.getHotels().stream().filter(hotel -> hotel.haveFreeRooms(dateFrom, dateTo, countPeople)).collect(Collectors.toSet());
//    }

    public Country getCountryByIdTown(Integer id) {
        var town = getTownById(id);
        return town.getCountry();
    }
}
