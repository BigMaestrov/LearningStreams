package ru.bigmaestrov.solution.model;

import lombok.Data;
import java.util.List;

@Data
public class CarMaker {
    private String carMakerName;
    private List<Car> carList;

    public CarMaker(String s, List<Car> cars) {
        setCarMakerName(s);
        setCarList(cars);
    }

    public CarMaker(CarMaker carMaker) {
        this(carMaker.getCarMakerName(), carMaker.getCarList());
    }

    @Override
    public String toString(){
        String cars ="";
        for(int i=0;i<carList.size();i++){
            cars+="["+carList.get(i).toString()+"]";
        }
        return getCarMakerName()+": "+cars;
    }
}
