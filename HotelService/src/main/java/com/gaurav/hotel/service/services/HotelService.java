package com.gaurav.hotel.service.services;

import java.util.List;

import com.gaurav.hotel.service.entities.Hotel;

public interface HotelService {

	// create
	
	Hotel create(Hotel hotel);
	
	// get all hotel
	
	List<Hotel> getAll();
	
	// get single hotel
	
	Hotel get(String id);
}
