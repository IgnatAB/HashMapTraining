package HashMapTraining;

import java.util.HashMap;
import java.util.Objects;

public class HashMapTrain {
    public static void main(String[] args) {

        Phones ph1= new Phones("iphone", "3G");
        Phones ph2= new Phones("iphone","5");
        Phones ph3= new Phones("samsung", "S10");
        Phones ph4= new Phones("xiaomi","RedMe 12");




        //Для демонстрации трансформации односвязного списка в дерево
        //создадим коллекцию смартфонов, посмотрим, действительно ли они располагаются списком.
        // Затем добавим элементы в коллекцию таким образом, чтобы попасть в критерии изменения
        // и проверим порядок снова.



        HashMap<Phones,Double> priceOfSmartphones = new HashMap<>();
        priceOfSmartphones.put(ph2, 12.0);
        priceOfSmartphones.put(ph3, 100.4);
        priceOfSmartphones.put(ph4, 101.8);
        priceOfSmartphones.put(ph1, 10.5);
        System.out.println(priceOfSmartphones);


    }
}


class Phones{
    String company;
    String model;

    public Phones(String company, String model) {
        this.company = company;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phones phones = (Phones) o;
        return Objects.equals(company, phones.company) && Objects.equals(model, phones.model);
    }

    @Override
    public int hashCode() {
        //переопределим метод хэшкода таким образом, чтобы искусственно создать коллизии.
        return Objects.hash(company, model);
    }

    @Override
    public String toString() {
        return "Phones{" +
                "company='" + company + '\'' +
                ", model=" + model +
                '}';
    }
}