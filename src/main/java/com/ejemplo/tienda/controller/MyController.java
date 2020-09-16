package com.ejemplo.tienda.controller;

import com.ejemplo.tienda.bean.City;
import com.ejemplo.tienda.service.ICityService;
import com.ejemplo.tienda.util.GeneratePdfReport;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@Autowired
	ICityService cityService;

	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> citiesReport() throws IOException {
		List<City> cities = (List<City>) cityService.findAll();

//The GeneratePdfReport.citiesReport() generates PDF file from the list of cities using iText library
		ByteArrayInputStream bis = GeneratePdfReport.citiesReport(cities);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=Informe_Ciudades.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
