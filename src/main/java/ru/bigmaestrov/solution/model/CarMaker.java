package ru.bigmaestrov.solution.model;

import lombok.Data;

@Data
public class CarMaker {

    private String carModel;
    private String carMaker;
    private String carModelYear;
    private String color;
    @Override
    public String toString(){
        return getCarModel()+" "+getCarMaker()+" "+getCarModelYear()+" "+getColor();
    }
}
