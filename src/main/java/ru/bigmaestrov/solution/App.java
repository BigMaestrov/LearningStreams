package ru.bigmaestrov.solution;

import ru.bigmaestrov.solution.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    //Основной метод приложения
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/CAR_DATA.csv";
        try {
            List<Car> products = parseCsv(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Проверка является ли колонка частью предыдущей колонки
    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        //Если в тексте одна ковычка и текст на нее заканчиваеться значит это часть предыдущей колонки
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }


    private static List<Car> parseCsv(String filePath) throws IOException {
        //Загружаем строки из файла
        List<Car> products = new ArrayList<Car>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < splitedText.length; i++) {
                //Если колонка начинается на кавычки или заканчиваеться на кавычки
                if (IsColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            Car car = new Car();
            car.setCarModel(columnList.get(0));
            car.setCarMaker(columnList.get(1));
            car.setCarModelYear(columnList.get(2));
            car.setColor(columnList.get(3));
            products.add(car);
        }
        return products;
    }
}
