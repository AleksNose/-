package com.aleksnose.hoteru.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
public class Town {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;
    private String Name;

    @ManyToOne
    @JoinColumn(name="IdCountry")
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "town", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Hotel> hotels;
}
