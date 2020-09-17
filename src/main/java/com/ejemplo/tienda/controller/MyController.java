package com.ejemplo.tienda.controller;

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

import com.ejemplo.tienda.bean.City;
import com.ejemplo.tienda.service.ICityService;
import com.ejemplo.tienda.util.GeneratePdfReport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api
@RestController
@RequestMapping("miTienda/")
public class MyController {
	@Autowired
	ICityService cityService;

	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	@ApiOperation(value = "pdf", nickname = "pdf", response = GeneratePdfReport.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
    @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
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
