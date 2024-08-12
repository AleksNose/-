//package com.aleksnose.hoteru.controller;
//
//import com.aleksnose.hoteru.models.Hotel;
//import com.aleksnose.hoteru.models.Reservation;
//import com.aleksnose.hoteru.models.User;
//import com.aleksnose.hoteru.service.UserService;
//import org.springframework.web.bind.annotation.*;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/api")
//public class UserController {
//    private UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping(path="/user/login")
//    public void login(String name, String surname) {
//        userService.login(name, surname);
//    }
//
//    @PostMapping(path="user/logout")
//    public void logout() {
//        userService.logout();
//    }
//
//    @GetMapping(path = "user/reservations")
//    public Set<Reservation> getReservations() {
//        return userService.getReservations();
//    }
//
//    @GetMapping(path = "/worker/hotels")
//    public Set<Hotel> getWorkedHotelsByUser(User user) {
//        return userService.getWorkedHotelsByUser(user);
//    }
//}
