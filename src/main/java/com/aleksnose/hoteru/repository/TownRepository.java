package com.aleksnose.hoteru.repository;

import com.aleksnose.hoteru.models.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TownRepository extends JpaRepository<Town, Integer> {
    List<Town> findByCountryId(Integer idCountry);
}
