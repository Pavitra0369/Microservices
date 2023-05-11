package com.gaurav.hotel.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaurav.hotel.service.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
