package HashMapTraining;

import java.lang.reflect.Field;
import java.util.*;

public class HashMapTrain {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        //Для демонстрации трансформации односвязного списка в дерево
        //создадим класс смартфонов, проверим, правильно ли мы переопределили equals(),hashCode() и toString.
        // Затем добавим смартфоны в коллекцию. Проверим результат.
        // Чтобы попасть в критерии изменения структуры, можем варьировать максимальное значение итератора в цикле.
        // Смотрим изменения в логах.

        Phones sm1 = new Phones("iphone","3g");
        Phones sm2 = new Phones("iphone","5");
        //проверяем одинаковые ли hashCode?
        System.out.println("hashCode объекта " + sm1 + ": " + sm1.hashCode());
        System.out.println("hashCode объекта " + sm2 + ": " + sm2.hashCode());

        HashMap<Phones, String> priceOfSmartphones = new HashMap<>();

        priceOfSmartphones.put(sm1, "10$");
        priceOfSmartphones.put(sm2, "20$");
        System.out.println("Список ключей: " + priceOfSmartphones.keySet());
        System.out.println("Список значений: " + priceOfSmartphones.values());

        for (int i = 1; i <= 9; i++) {
            priceOfSmartphones.put(new Phones("iphone", String.valueOf(i+5)), String.valueOf(i * 100) + "$");
        }
        System.out.println("После добавления новых элементов наша HashMap priceOfSmartphones содержит: ");
        System.out.println(priceOfSmartphones);

        System.out.println("Текущее количество элементов в HashMap: " + priceOfSmartphones.size());

        // с помощью рефлексии проверяем капасити мапы и тип узлов
        Field capacityField1 = HashMap.class.getDeclaredField("threshold");
        capacityField1.setAccessible(true);

        double capacity = ((int) capacityField1.get(priceOfSmartphones)) / 0.75; //т.к. поле threshold = capacity*loadFacktor
        System.out.println("Текущая емкость HashMap: " + capacity);



        Field tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] table = (Object[]) tableField.get(priceOfSmartphones);

        for (Object node : table) {
            if (node != null) {
                // Используем рефлексию для проверки типа узла
                Class<?> nodeClass = node.getClass();
                System.out.println("Node class: " + nodeClass.getName());

                // Проверяем, является ли узел одного из типов
                if (nodeClass.getName().equals("java.util.HashMap$Node")) {
                    System.out.println("Это обычный узел (Regular Node)");
                } else if (nodeClass.getName().equals("java.util.HashMap$TreeNode")) {
                    System.out.println("Это дерево узел (Tree Node)");
                }
            }
        }
    }
}

class Phones {
    String smartphone;
    String model;

    public Phones(String company, String model) {
        this.smartphone = company;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phones phones = (Phones) o;
        return Objects.equals(smartphone, phones.smartphone) && Objects.equals(model, phones.model);
    }

    @Override
    public int hashCode() {

        //переопределим метод хэшкода таким образом, чтобы искусственно создать коллизии.
        return smartphone.length();
    }

    @Override
    public String toString() {
        return "{"+ smartphone + " " + model + "}";
    }
}
