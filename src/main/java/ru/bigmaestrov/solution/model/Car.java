package ru.bigmaestrov.solution.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Comparator;

@Data
public class Car implements Comparable<Car> {
    private String carModel;
    private String carMaker;
    private String carModelYear;
    private String color;

    @Override
    public String toString(){
        return "{"+getCarModel()+" | "+getCarMaker()+" | "+getCarModelYear()+" | "+getColor()+"}";
    }

    public boolean isCorrectCar(){
        return !(getColor().equals("") || getCarModel().equals("") || getCarModelYear().equals("") || getCarMaker().equals(""));
    }

    @Override
    public int compareTo(Car car) {
        return carMaker.compareTo(car.carMaker);
    }
}
