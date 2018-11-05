package com.skyobserver.repository;


import com.skyobserver.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportsRepository extends CrudRepository<Airport, Long> {

    List<Airport> findAirportsByMunicipalityStartingWith(String expression);
    Airport findAirportByIataCode(String iataCode);
}
