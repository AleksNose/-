package com.aleksnose.hoteru.service;

import com.aleksnose.hoteru.DTO.HotelDTO;
import com.aleksnose.hoteru.exception.BadRequestException;
import com.aleksnose.hoteru.exception.NotFoundException;
import com.aleksnose.hoteru.models.*;
import com.aleksnose.hoteru.repository.HotelRepository;
import com.aleksnose.hoteru.repository.RoomRepository;
import com.aleksnose.hoteru.repository.TargetRoomRepository;
import com.aleksnose.hoteru.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HotelService {
    private HotelRepository repository;
    private TownRepository townRepository;
    private TargetRoomRepository targetRoomRepository;
    private RoomRepository roomRepository;


    @Autowired
    private HotelService(HotelRepository repository, TownRepository townRepository, TargetRoomRepository targetRoomRepository, RoomRepository roomRepository) {
        this.repository = repository;
        this.townRepository = townRepository;
        this.targetRoomRepository = targetRoomRepository;
        this.roomRepository = roomRepository;
    }

    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    public Hotel getHotelById(Integer id) {
        var hotel = repository.findById(id);
        return hotel.orElseThrow(() -> new NotFoundException("Hotel not found with id: " + id));
    }

    public Hotel saveHotel(String name, Integer idTown) {
        var hotel = new Hotel();
        var town = getTownById(idTown);
        hotel.setName(name);
        hotel.setTown(town);
        return repository.save(hotel);
    }

    public Hotel saveHotel(Hotel hotel, HotelDTO hotelDTO) {
        var town = getTownById(hotelDTO.getIdTown());
        hotel.setName(hotelDTO.getName());
        hotel.setTown(town);
        return repository.save(hotel);
    }

    public Set<TargetRoom> getTargetRoomsByHotelId(Integer id) {
        var hotel = getHotelById(id);
        return hotel.getTargetRooms();
    }

    public Set<User> getWorkersByHotel(Integer id) {
        var hotel = getHotelById(id);
        return hotel.getWorkersData();
    }

    public Town getTownById(Integer id) {
        return townRepository.findById(id).orElseThrow(() -> new NotFoundException("Town not found with id: " + id));
    }

    public User getAdminByHotel(Integer id) {
        var hotel = getHotelById(id);
        return hotel.getAdmin();
    }

    public void addRoomsByHotel(Integer id, Integer countRooms, Integer countPeopleInRoom) {
        var hotel = getHotelById(id);
        var targetRoom = new TargetRoom();
        Set<Room> rooms = new HashSet<>();

        if (countRooms <= 0 || countPeopleInRoom <= 0)
            throw new BadRequestException("Variable countRooms or countPeopleInRoom is less than or equal to 0");

        for (int i = 0; i < countRooms; i++) {
            var room = new Room();
            room.setTargetRoom(targetRoom);
            rooms.add(room);
            roomRepository.save(room);
        }

        targetRoom.setRooms(rooms);
        targetRoom.setHotel(hotel);

        repository.save(hotel);
        targetRoomRepository.save(targetRoom);
    }

//        public Room getFreeRoom(Integer idHotel, LocalDate dateFrom, LocalDate dateTo, int countPeople) {
//        var hotel = repository.findById(idHotel).get();
//        var targetRoom = hotel.getFreeTargetRooms(dateFrom, dateTo, countPeople).stream().findAny().get();
//        var room = targetRoom.getFreeRoom(dateFrom, dateTo);
//        return room;
//    }
}
