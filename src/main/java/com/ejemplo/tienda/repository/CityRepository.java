package com.ejemplo.tienda.repository;

import com.ejemplo.tienda.bean.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
