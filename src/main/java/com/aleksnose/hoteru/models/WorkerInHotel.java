package com.aleksnose.hoteru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class WorkerInHotel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;
    private boolean IsAdmin;

    @ManyToOne
    @JoinColumn(name="IdUser")
    private User user;

    @ManyToOne
    @JoinColumn(name="IdHotel")
    private Hotel hotel;
}
