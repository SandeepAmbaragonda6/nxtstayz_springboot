package com.example.nxtstayz.controller;

import java.util.List;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.service.HotelJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {
    @Autowired
    private HotelJpaService hotelJpaService;

    @GetMapping("/hotels")
    public List<Hotel> getHotels() {
        return hotelJpaService.getHotels();
    }

    @GetMapping("/hotels/{hotelId}/rooms")
    public List<Room> getHotelRooms(@PathVariable("hotelId") int hotelId) {
        return hotelJpaService.getHotelRooms(hotelId);
    }

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotelById(@PathVariable("hotelId") int hotelId) {
        return hotelJpaService.getHotelById(hotelId);
    }

    @PostMapping("/hotels")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelJpaService.addHotel(hotel);
    }

    @PutMapping("/hotels/{hotelId}")
    public Hotel updateHotel(@PathVariable("hotelId") int hotelId, @RequestBody Hotel hotel) {
        return hotelJpaService.updateHotel(hotelId, hotel);
    }

    @DeleteMapping("/hotels/{hotelId}")
    public void deleteHotel(@PathVariable int hotelId) {
        hotelJpaService.deleteHotel(hotelId);
    }
}