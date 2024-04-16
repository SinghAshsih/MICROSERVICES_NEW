package com.microservices.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.user.service.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")

public interface HotelService {

	@GetMapping("/hotel/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
