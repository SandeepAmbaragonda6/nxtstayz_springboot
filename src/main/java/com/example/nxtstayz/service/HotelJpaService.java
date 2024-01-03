package com.example.nxtstayz.service;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.repository.HotelJpaRepository;
import com.example.nxtstayz.repository.HotelRepository;
import com.example.nxtstayz.repository.RoomJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelJpaService implements HotelRepository {
    @Autowired
    private HotelJpaRepository hotelJpaRepository;

    @Autowired
    private RoomJpaRepository roomJpaRepository;

	private int hotelId;

    @Override
    public ArrayList<Hotel> getHotels() {
        return (ArrayList<Hotel>) hotelJpaRepository.findAll();
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        try {
            return hotelJpaRepository.findById(hotelId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        return hotelJpaRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(int hotelId, Hotel hotel) {
        try {
            Hotel newHotel = hotelJpaRepository.findById(hotelId).get();
            if (hotel.getHotelName() != null) {
                newHotel.setHotelName(hotel.getHotelName());
            }
            if (hotel.getLocation() != null) {
                newHotel.setLocation(hotel.getLocation());
            }
            if (hotel.getRating() != 0) {
                newHotel.setRating(hotel.getRating());
            }
            return hotelJpaRepository.save(newHotel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteHotel(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            List<Room> rooms = roomJpaRepository.findById(hotel);
            for (Room room : rooms) {
                room.setHotel(null);
            }
            roomJpaRepository.saveAll(rooms);
            hotelJpaRepository.deleteById(hotelId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Room> getHotelRooms(int hotelId) {
        this.hotelId = hotelId;
		try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            return roomJpaRepository.findById(hotel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}