package com.lcwd.hotel.servises;

import java.util.List;

import com.lcwd.hotel.entity.Hotel;

public interface HotelService {
	Hotel create(Hotel hotel);

	List<Hotel> getAll();

	Hotel get(String id);

	void delete(String id);

	Hotel update(Hotel hotel);

}
