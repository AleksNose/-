package com.aleksnose.hoteru.controller;

import com.aleksnose.hoteru.DTO.HotelDTO;
import com.aleksnose.hoteru.DTO.TargetRoomDTO;
import com.aleksnose.hoteru.Mapper.HotelMapper;
import com.aleksnose.hoteru.Mapper.TargetRoomMapper;
import com.aleksnose.hoteru.models.User;
import com.aleksnose.hoteru.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public List<HotelDTO> getAllHotels() {
        var hotels = service.getAllHotels();
        return hotels.stream().map(HotelMapper.INSTANCE::hotelToHotelDTO).collect(Collectors.toList());
    }

    @PostMapping()
    public HotelDTO saveHotel(String name, Integer idTown) {
        var hotel = service.saveHotel(name, idTown);
        return HotelMapper.INSTANCE.hotelToHotelDTO(hotel);
    }

    @PutMapping(path = "/{id}")
    public HotelDTO modifiedHotel(@PathVariable Integer id, @RequestBody HotelDTO hotelDTO)
    {
        var hotel = service.getHotelById(id);
        service.saveHotel(hotel, hotelDTO);

        return HotelMapper.INSTANCE.hotelToHotelDTO(hotel);
    }

    @PostMapping(path = "/{id}/rooms")
    public void addRooms(@PathVariable int id, @PathVariable int countRooms, @PathVariable int countPeopleInRoom) {
        service.addRoomsByHotel(id, countRooms, countPeopleInRoom);
    }

    @GetMapping(path = "/{id}")
    public HotelDTO getHotelById(@PathVariable Integer id) {
        var hotel = service.getHotelById(id);
        return HotelMapper.INSTANCE.hotelToHotelDTO(hotel);
    }

    @GetMapping(path = "/{id}/targetrooms")
    public Set<TargetRoomDTO> getRoomsForHotel(@PathVariable Integer id) {
        var roomsForHotel = service.getTargetRoomsByHotelId(id);
        return roomsForHotel.stream().map(TargetRoomMapper.INSTANCE::targetRoomToTargetRoomDTO).collect(Collectors.toSet());
    }

    @GetMapping(path = "/{id}/workers")
    public Set<User> getWorkersByHotel(@PathVariable Integer id) {
        return service.getWorkersByHotel(id);
    }

    @GetMapping(path = "/{id}/admin")
    public User getAdminByHotel(@PathVariable Integer id) {
        return service.getAdminByHotel(id);
    }
}
