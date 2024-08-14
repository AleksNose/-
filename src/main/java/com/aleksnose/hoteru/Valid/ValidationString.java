package com.aleksnose.hoteru.Valid;


import com.aleksnose.hoteru.exception.BadRequestException;

public class ValidationString {

    public static boolean isValid(String parameter, String arg) {
        if (arg.isBlank() || arg.isEmpty())
            throw new BadRequestException(parameter + " is not valid");

        return true;
    }
}
