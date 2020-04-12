package com.voronov.TravelingBotDemo.repo;

import com.voronov.TravelingBotDemo.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByName(String name);

}
