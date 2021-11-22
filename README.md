# LearningStreams

## Используемый стек технологий
- Java 8
- Maven 3.6.3
- Stream API
- Lombok
- Java NIO
- Reflection API
## Задачи
1. Провести анализ данных, представленных в файле CAR_DATA.csv;
2. Создать классы:
- Car - автомобиль;
- CarMaker - производитель автомобиля;
3. Получить данные из CAR_DATA.csv;
4. Выполнить очистку данных;
5. Преобразовать из реляционной в объектно-ориентированную модель данных:
- Преобразовать строку в массив строк;
- Преобразовать массив строк в объекты класса Car;
- Вывести результат построчно в файл;
6. Группировка по цветам:
- Преобразовать объекты класса Car в Map, где:
- ключ - цвет автомобиля;
- значение - информация об автомобиле;
- Вывести результат преобразований построчно в файл;
7. Преобразовать объекты класса Car в объекты класса CarMaker:
- Преобразовать объекты класса Car в Map, где:
- ключ - производитель автомобиля;
- значение - список с информация об автомобилях;
- Преобразовать Map в List типа CarMaker;
- Вывести результат построчно в файл;
- Вывести производителей в консоль, в виде массива используя Collectors.joining;
8. Получить список автомобилей:
- Оставить производителей, у которых есть больше чем 2 модели автомобиля;
- Отсортировать производителей, в алфовитном порядке;
- Вывести результат построчно в файл.
