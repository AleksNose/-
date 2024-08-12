package com.aleksnose.hoteru.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;
    private int NrRoom;

    @ManyToOne
    @JoinColumn(name="IdTargetRoom")
    private TargetRoom targetRoom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Reservation> reservations;

    public boolean isFreeRoom(LocalDate dateFrom, LocalDate dateTo)
    {
        return reservations.stream().noneMatch(reservation ->
                        !(dateTo.isBefore(reservation.getDateFrom()) || dateFrom.isAfter(reservation.getDateTo())));
    }
}
