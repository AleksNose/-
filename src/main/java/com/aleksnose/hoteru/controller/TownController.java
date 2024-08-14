package com.aleksnose.hoteru.controller;

import com.aleksnose.hoteru.DTO.TownDTO;
import com.aleksnose.hoteru.Mapper.TownMapper;
import com.aleksnose.hoteru.service.TownService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/town")
public class TownController {
    private TownService service;

    public TownController(TownService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public TownDTO getTownById(Integer id) {
        var town = service.getTownById(id);
        return TownMapper.INSTANCE.townToTownDTO(town);
    }

    @GetMapping("/all")
    public List<TownDTO> getAllTowns() {
        var towns = service.getAllTowns();
        return towns.stream().map(TownMapper.INSTANCE::townToTownDTO).collect(Collectors.toList());
    }

    @GetMapping("/all/byCountry/{idCountry}")
    public List<TownDTO> getTownsByCountryId(Integer idCountry) {
        var towns = service.getAllTownsByIdCountry(idCountry);
        return towns.stream().map(TownMapper.INSTANCE::townToTownDTO).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public TownDTO modifiedTown(@RequestBody TownDTO townDTO) {
        var town = service.modifiedTownById(townDTO);
        return TownMapper.INSTANCE.townToTownDTO(town);
    }

    @PostMapping
    public TownDTO saveTown(@RequestBody TownDTO townDTO) {
        var town = service.saveTown(townDTO);
        return TownMapper.INSTANCE.townToTownDTO(town);
    }
}
