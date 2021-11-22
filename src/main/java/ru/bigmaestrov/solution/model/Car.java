package ru.bigmaestrov.solution.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;

@Data
public class Car {
    private String carModel;
    private CarMaker carMaker;
    private String carModelYear;
    private String color;

    @Override
    public String toString(){
        return "{"+getCarModel()+" | "+getCarMaker()+" | "+getCarModelYear()+" | "+getColor()+"}";
    }

    public boolean hasEmptyField(){
        return !(getColor().equals("") || getCarModel().equals("") || getCarModelYear().equals("") || getCarMaker().equals(""));
    }
}
