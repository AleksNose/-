package com.aleksnose.hoteru.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Integer Id;
    private String name;
    private String surname;
    private Boolean IsWorker;
}
