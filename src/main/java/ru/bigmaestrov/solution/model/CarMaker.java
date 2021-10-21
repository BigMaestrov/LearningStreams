package ru.bigmaestrov.solution.model;

import lombok.Data;
import java.util.List;

@Data
public class CarMaker {
    private String carMakerName;
    private List<Car> carList;

    @Override
    public String toString(){
        String cars ="";
        for(int i=0;i<carList.size();i++){
            cars+="["+carList.get(i).toString()+"]";
        }
        return getCarMakerName()+": {"+cars+"}";
    }
}
