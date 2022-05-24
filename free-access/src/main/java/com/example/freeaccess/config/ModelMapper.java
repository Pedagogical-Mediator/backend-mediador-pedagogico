package com.example.freeaccess.config;

import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public org.modelmapper.ModelMapper modelMapper(){
        return new org.modelmapper.ModelMapper();
    }

}
