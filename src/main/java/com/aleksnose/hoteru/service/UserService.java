//package com.aleksnose.hoteru.service;
//
//import com.aleksnose.hoteru.models.Hotel;
//import com.aleksnose.hoteru.models.Reservation;
//import com.aleksnose.hoteru.models.User;
//import com.aleksnose.hoteru.models.WorkerInHotel;
//import com.aleksnose.hoteru.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class UserService {
//    public static User loginUser;
//    private UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User getUserById(Integer id) {
//        return userRepository.findById(id).orElse(null);
//    }
//
//
//    public void login(String name, String surname) {
//        var foundUser = userRepository.findByNameAndSurname(name, surname);
//        if (foundUser == null) return;
//        loginUser = foundUser;
//    }
//
//    public void logout() {
//        loginUser = null;
//    }
//
//    public Set<Reservation> getReservations() {
//        return loginUser.getReservations();
//    }
//
//    public Set<Hotel> getWorkedHotelsByUser(User user) {
//        return user.getWorkersInHotels().stream().map(WorkerInHotel::getHotel).collect(Collectors.toSet());
//    }
//
//    public Set<Hotel> getWorkedHotelsByUser() {
//        return UserService.loginUser.getWorkersInHotels().stream().map(WorkerInHotel::getHotel).collect(Collectors.toSet());
//    }
//
//    public Set<User> getWorkersByHotel(Hotel hotel) {
//        return hotel.getWorkerInHotels().stream().map(WorkerInHotel::getUser).collect(Collectors.toSet());
//    }
//}
