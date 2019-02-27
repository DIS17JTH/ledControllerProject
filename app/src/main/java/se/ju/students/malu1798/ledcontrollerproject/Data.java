package se.ju.students.malu1798.ledcontrollerproject;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Human> humans = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            humans.add(new Human(
                    i,
                    "Human #" + i,
                    (int) (Math.random() * 100)
            ));
        }
    }


    public static class Human {
        public int id;
        public String name;
        public int age;

        public Human(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }

}
