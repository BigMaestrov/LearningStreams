package ru.bigmaestrov.solution;

import ru.bigmaestrov.solution.model.Car;
import ru.bigmaestrov.solution.model.CarMaker;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    //Основной метод приложения
    public static void main(String[] args) throws IOException {
        String filePath1 = "src/main/resources/CAR_DATA.csv";
        String filePath2 = "src/main/resources/fileForWrite(Task5).csv";
        String filePath6 = "src/main/resources/fileForWrite(Task6).csv";
        String filePath7 = "src/main/resources/fileForWrite(Task7).csv";
        String filePath8 = "src/main/resources/fileForWrite(Task8).csv";
        try {
            //Задание 3-5
            List<Car> products = parseCsv(filePath1);
            writeInFile(filePath2, products);
            //Задание 6
            Map<String, List<Car>> carsGroupByColor = groupingByColors(products);
            writeInFile(filePath6, carsGroupByColor);
            //Задание 7
            Map<CarMaker, List<Car>> carsGroupByMaker = groupingByMaker(products);
            List<CarMaker> carMakers = convertCarMapToCarMakerList(carsGroupByMaker);
            writeCarMakerInFile(filePath7, carsGroupByMaker);
            printCarMakers(carMakers);
            //Задание 8
            List<Car> cars = products;
            deleteLowCarMaker(cars, carsGroupByMaker);
            Collections.sort(cars);
            writeInFile(filePath8,cars);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Проверка является ли колонка частью предыдущей колонки
    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        //Если в тексте одна ковычка и текст на нее заканчивается значит это часть предыдущей колонки
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }

    private static List<Car> parseCsv(String filePath) throws IOException {
        //Загружаем строки из файла
        List<Car> products = new ArrayList<Car>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] splitTextText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < splitTextText.length; i++) {
                //Если колонка начинается на кавычки или заканчиваеться на кавычки
                if (IsColumnPart(splitTextText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + splitTextText[i]);
                } else {
                    columnList.add(splitTextText[i]);
                }
            }
            Car car = new Car();
            car.setCarModel(columnList.get(0));
            car.setCarMaker(new CarMaker(columnList.get(1), new ArrayList<Car>()));
            car.setCarModelYear(columnList.get(2));
            car.setColor(columnList.get(3));
            if (car.hasEmptyField()) {
                products.add(car);
            }

        }
        return products;
    }

    //Построчный вывод в файл
    public static void writeInFile(String filePath, List<Car> cars) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < cars.size(); i++) {
            writer.write(cars.get(i).toString() + "\n");
        }
        writer.close();
    }

    //Построчный вывод в файл
    public static void writeCarMakerInFile(String filePath, Map<CarMaker, List<Car>> cars) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Map.Entry<CarMaker, List<Car>> carMaker : cars.entrySet()) {
            writer.write(carMaker.toString() + "\n");
        }
        writer.close();
    }

    public static void writeCarMakerInFile(String filePath, List<CarMaker> carMakers) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (CarMaker carMaker : carMakers) {
            writer.write(carMaker.toString() + "\n");
        }
        writer.close();
    }

    //преобразование карты машин в список производителей
    public static List<CarMaker> convertCarMapToCarMakerList(Map<CarMaker, List<Car>> carsGroup) {
        List<CarMaker> keyList = new ArrayList<CarMaker>(carsGroup.keySet());
        List<CarMaker> carMakerList = new ArrayList<CarMaker>();
        for (int i = 0; i < carsGroup.size(); i++) {
            carMakerList.add(new CarMaker(keyList.get(i)));
        }
        return carMakerList;
    }

    //Построчный вывод в файл
    public static void writeInFile(String filePath, Map<String, List<Car>> carsGroup) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (HashMap.Entry<String, List<Car>> entry : carsGroup.entrySet()) {
            writer.write("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
        }
        writer.close();
    }

    //Группировка машин по цветам
    public static Map<String, List<Car>> groupingByColors(List<Car> cars) {
        return cars.stream().collect(Collectors.groupingBy(Car::getColor));
    }

    //Группировка машин по производителю
    public static Map<CarMaker, List<Car>> groupingByMaker(List<Car> cars) {
        return cars.stream().collect(Collectors.groupingBy(Car::getCarMaker));
    }

    public static void printCarMakers(List<CarMaker> carMakers) {
        System.out.println(carMakers.stream()
                .map(CarMaker::getCarMakerName)
                .collect(Collectors.joining(", ")));
    }

    public static List<Car> deleteLowCarMaker(List<Car> cars, Map<CarMaker, List<Car>> carMakers) {
        for (int i = 0; i < cars.size(); i++) {
            if (carMakers.get(cars.get(i).getCarMaker()).size() < 2) {
                cars.remove(i);
            }
        }
        return cars;
    }

    public static List<CarMaker> deleteLowCarMaker(List<CarMaker> carMakers) {
        return carMakers.stream().filter(x -> x.getCarList().size() <2 ).collect(Collectors.toList());
    }

}
