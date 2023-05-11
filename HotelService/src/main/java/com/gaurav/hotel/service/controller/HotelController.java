package com.gaurav.hotel.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaurav.hotel.service.entities.Hotel;
import com.gaurav.hotel.service.services.HotelService;

@RestController
public class HotelController {
	@Autowired
	private HotelService hotelService;
	
	// create
	@PostMapping("/hotels")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}
	
	// get single hotel using id
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<Hotel> get(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
	}
	
	
	// get all hotels
	@GetMapping("/hotels")
	public ResponseEntity<List<Hotel>> getAll(){
		return ResponseEntity.ok(hotelService.getAll());
	}
	
	
}
