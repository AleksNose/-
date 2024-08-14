package com.aleksnose.hoteru.service;

import com.aleksnose.hoteru.DTO.ReservationDTO;
import com.aleksnose.hoteru.exception.NotFoundException;
import com.aleksnose.hoteru.models.Reservation;
import com.aleksnose.hoteru.models.TargetRoom;
import com.aleksnose.hoteru.models.User;
import com.aleksnose.hoteru.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation getReservationById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found with id: " + id));
    }

    public Reservation saveReservation(ReservationDTO reservationDTO)
    {
        var reservation = new Reservation();

        return reservation;
    }

    public TargetRoom getReservationTargetRoom(Integer id) {
        var reservation = getReservationById(id);
        return reservation.getRoom().getTargetRoom();
    }

    public User getClientByReservationId(Integer id) {
        var reservation = getReservationById(id);
        return reservation != null ? reservation.getUser() : null;
    }
}
