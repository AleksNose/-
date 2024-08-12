package com.aleksnose.hoteru.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ComponentsInRoom {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @ManyToOne
    private TargetRoom targetRoom;

    @ManyToOne
    private Component component;
}
