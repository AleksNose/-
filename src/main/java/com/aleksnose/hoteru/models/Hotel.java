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
public class Hotel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;
    private String Name;

    @ManyToOne
    @JoinColumn(name="IdTown")
    private Town town;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TargetRoom> targetRooms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<WorkerInHotel> workerInHotels;

    public Set<TargetRoom> getFreeTargetRooms(LocalDate dateFrom, LocalDate dateTo, int countPeople) {
        return targetRooms.stream()
                .filter(targetRoom -> targetRoom.getCountFreeRooms(dateFrom, dateTo) > 0 && targetRoom.getCountPeople() == countPeople)
                .collect(Collectors.toSet());
    }

    public Set<User> getWorkersData() {
        return workerInHotels.stream().map(WorkerInHotel::getUser).collect(Collectors.toSet());
    }

    public User getAdmin() {
        return workerInHotels.stream().filter(WorkerInHotel::isIsAdmin).map(WorkerInHotel::getUser).findAny().get();
    }
}
