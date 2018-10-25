package com.skyobserver.repository;


import com.skyobserver.model.Airport;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AirportsRepository extends CrudRepository<Airport, Long> {

    List<Airport> findAirportsByMunicipalityStartingWith(String expression);
    Airport findAirportByIataCode(String iataCode);
}
