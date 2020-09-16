package com.ejemplo.tienda.service;

import com.ejemplo.tienda.bean.City;
import com.ejemplo.tienda.repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

	@Autowired
	private CityRepository repository;

	@Override
	public List<City> findAll() {
		List<City> cities = (List<City>) repository.findAll();
		return cities;
	}
}
