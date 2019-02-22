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

    public static class Colors {
        private ArrayList<String> colors;

        public Colors() {
            colors = new ArrayList<>();
            colors.add("#258174");
            colors.add("#27AE60");
            colors.add("#3498DB");
            colors.add("#CB4335");
            colors.add("#34495E");
            colors.add("#F4D03F");

        }

        public Colors(String color) {
            colors = new ArrayList<>();
            colors.add("#258174");
            colors.add("#27AE60");
            colors.add("#3498DB");
            colors.add("#CB4335");
            colors.add("#34495E");
            colors.add("#F4D03F");
            colors.add(color);
        }

        public Colors(ArrayList<String> colors) {
            this.colors = colors;
        }


        /*GETTERS AND SETTERS*/
        public ArrayList<String> getColors() {
            return this.colors;
        }

        public void setColors(ArrayList<String> colors) {
            this.colors = colors;
        }

        public void addColor(String color) {
            this.colors.add(color);
        }

        public String getColor(int index) {
            return this.colors.get(index);
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
