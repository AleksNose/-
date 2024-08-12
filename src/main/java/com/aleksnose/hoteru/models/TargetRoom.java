package com.aleksnose.hoteru.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class TargetRoom {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;
    private int CountPeople;
    private int CountTargetRoom;

    @ManyToOne
    @JoinColumn(name = "IdHotel")
    private Hotel hotel;

    @OneToMany(mappedBy = "targetRoom", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Room> rooms;

    @OneToMany(mappedBy = "targetRoom", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ComponentsInRoom> componentsInRooms;

    public int getCountFreeRooms(LocalDate dateFrom, LocalDate dateTo)
    {
        return (int) rooms.stream().filter(room -> room.isFreeRoom(dateFrom, dateTo)).count();
    }

    public Set<Component> getComponents()
    {
        return componentsInRooms.stream().map(ComponentsInRoom::getComponent).collect(Collectors.toSet());
    }
}
