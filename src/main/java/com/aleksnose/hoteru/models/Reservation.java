package com.aleksnose.hoteru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;
    private LocalDate DateFrom;
    private LocalDate DateTo;

    @ManyToOne
    @JoinColumn(name="IdUser")
    private User user;

    @ManyToOne
    @JoinColumn(name="IdRoom")
    private Room room;
}
